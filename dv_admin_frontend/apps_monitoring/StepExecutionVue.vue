<template>
  <div class="flex flex-col gap-5 h-full" v-loading="loading">
    <div class="flex justify-between items-center">
      <div class="search-container flex-grow">
        <template v-if="!isMultiSearch">
          <div class="flex flex-col gap-2">
            <div class="flex items-center gap-2 mb-2">
              <el-button type="primary" @click="addSearchField">조건 추가</el-button>
            </div>
            <div v-for="(field, index) in searchFields" :key="index" class="flex items-center gap-2">
              <el-select v-model="field.selectedField" placeholder="검색 조건을 선택하세요." class="search-select">
                <el-option label="StepExecutionID" value="stepExecutionId"></el-option>
                <el-option label="JobExecutionID" value="jobExecutionId"></el-option>
                <el-option label="Start-End-Time" value="start"></el-option>
                <el-option label="Status" value="status"></el-option>
                <el-option label="StepName" value="stepName"></el-option>
              </el-select>
              <el-input
                v-if="field.selectedField === 'start'"
                v-model="field.startDate"
                placeholder="Start Date: ex)240101"
                class="search-input"
              >
              <template #prefix>
                  <el-icon><Search /></el-icon>
                </template>
              </el-input>
              <el-input
                v-if="field.selectedField === 'start'"
                v-model="field.endDate"
                placeholder="End Date: ex)241231"
                class="search-input"
              >
              <template #prefix>
                  <el-icon><Search /></el-icon>
                </template>
              </el-input>
              <el-select
                v-if="field.selectedField === 'status'"
                v-model="field.query"
                placeholder="상태를 선택하세요"
                class="search-input"
              >
                <el-option label="COMPLETED" value="COMPLETED"></el-option>
                <el-option label="FAILED" value="FAILED"></el-option>
                <el-option label="STARTED" value="STARTED"></el-option>
              </el-select>
              <el-input
                v-else-if="field.selectedField !== 'start' && field.selectedField !== 'status'"
                v-model="field.query"
                placeholder="검색어를 입력하세요."
                :disabled="!field.selectedField"
                class="search-input"
              >
              <template #prefix>
                  <el-icon><Search /></el-icon>
                </template>
              </el-input>
              <el-button type="danger" @click="removeSearchField(index)">X</el-button>
            </div>
            <el-button @click="handleSearch" :disabled="!isSearchValid" class="mt-2">
              확인
            </el-button>
          </div>
        </template>
      </div>
      <div class="flex gap-2">
        <el-button class="button-color" @click="handleRefresh">
          새로고침
        </el-button>
      </div>
    </div>

    <el-table :data="pagedData" style="width: 100%" max-height="800" border>
      <el-table-column
        v-for="(column, index) in displayColumns"
        :key="column.key"
        :prop="column.key"
        :label="column.label"
        :width="column.width"
        :fixed="index === 0 ? 'left' : false"
        align="center" 
        :header-align="'center'"  
      >
        <template #default="scope">
          <div
            @click="handleClick(scope.row, column.key)"
            :class="['clickable-cell', getCellClass(column.key), {
              'highlight-cell': scope.row.status === 'STARTED',
              'failed-cell': scope.row.status === 'FAILED'
            }]"
          >
            <template v-if="column.key === 'exitMessage'">
              <el-tooltip v-if="scope.row[column.key] && scope.row[column.key].length > 16" :content="scope.row[column.key]" placement="top">
                <span>{{ scope.row[column.key].substring(0, 16) + '...' }}</span>
              </el-tooltip>
              <span v-else>{{ scope.row[column.key] }}</span>
            </template>
            <template v-else>
              {{ scope.row[column.key] }}
            </template>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <Pagination
      :currentPage="currentPage"
      :pageSize="pageSize"
      :totalItems="totalItems"
      @update:currentPage="handlePageChange"
    />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch, computed } from "vue";
import { useStepExecutionStore } from "../../../../store/bath/monitoring/stepExecution";
import { ElNotification, ElMessageBox } from "element-plus";
import { defineProps, defineEmits } from 'vue';
import { TableColumn, StepExecutionList } from "../../../../types/batch/monitoring/stepExecution";
import { Search } from '@element-plus/icons-vue';
import Pagination from '../../../components/Pagination.vue';

const props = defineProps({
  selectedRow: Object,
  refresh: Boolean
});

const emits = defineEmits(['step-row-clicked']);

const stepExecutionStore = useStepExecutionStore();

const displayColumns = ref<TableColumn[]>([
  { key: "stepExecutionId", label: "Step Execution ID", width: 150 },
  { key: "stepName", label: "Step Name", width: 250 },
  { key: "jobExecutionId", label: "Job Execution ID", width: 150 },
  { key: "startTime", label: "Start Time", width: 180 },
  { key: "endTime", label: "End Time", width: 180 },
  { key: "duration", label: "Duration(s)", width: 100 },
  { key: "status", label: "Status", width: 150 },
  { key: "exitCode", label: "Exit Code", width: 150 },
  { key: "exitMessage", label: "Exit Message", width: 150 },
  { key: "commitCount", label: "Commit Count", width: 125 },
  { key: "readCount", label: "Read Count", width: 120 },
  { key: "filterCount", label: "Filter Count", width: 120 },
  { key: "writeCount", label: "Write Count", width: 120 },
  { key: "readSkipCount", label: "Read Skip Count", width: 140 },
  { key: "writeSkipCount", label: "Write Skip Count", width: 140 },
  { key: "processSkipCount", label: "Process Skip Count", width: 155 },
  { key: "rollbackCount", label: "Rollback Count", width: 150 }
]);

const dataList = ref<StepExecutionList>([]);
const currentPage = ref(1);
const pageSize = ref(16);
const totalItems = computed(() => dataList.value.length);
const loading = ref(false);

const isMultiSearch = ref(false);
const searchFields = ref([{ selectedField: '', query: '', startDate: '', endDate: '' }]);

const getFormattedDate = (date: Date) => {
  const year = date.getFullYear().toString().slice(2);
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const day = date.getDate().toString().padStart(2, '0');
  return `${year}${month}${day}`;
};

const getLast7Days = () => {
  const today = new Date();
  const last7Days = new Date(today);
  last7Days.setDate(today.getDate() - 7);
  return { startDate: getFormattedDate(last7Days), endDate: getFormattedDate(today) };
};

const isSearchValid = computed(() => {
  return searchFields.value.every(field => {
    if (field.selectedField === 'start') {
      const startDateValid = /^\d{6}$/.test(field.startDate);
      const endDateValid = field.endDate === '' || /^\d{6}$/.test(field.endDate);
      return startDateValid && endDateValid;
    }
    return field.selectedField && field.query.length > 0;
  });
});

const pagedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return dataList.value.slice(start, end);
});

onMounted(() => {
  fetchData();
});

watch(() => props.selectedRow, (newRow) => {
  if (newRow) {
    fetchExecutionStepExecutionById(newRow.jobExecutionId);
  } else {
    fetchData();
  }
});

const handleRefresh = async () => {
  searchFields.value = [{ selectedField: '', query: '', startDate: '', endDate: '' }];
  fetchData();
};

const fetchData = async () => {
  loading.value = true;
  try {
    const { startDate, endDate } = getLast7Days();
    await fetchStepExecutionByDate(startDate, endDate);
  } catch (error) {
    ElNotification({
      type: "error",
      title: "Error",
      message: "데이터를 가져오는 동안 오류가 발생했습니다"
    });
  } finally {
    loading.value = false;
  }
};

const fetchExecutionStepExecutionById = async (id: string) => {
  loading.value = true;
  try {
    await stepExecutionStore.getExecutionStepExecutionById(id);
    dataList.value = stepExecutionStore.StepExecutionList;
  } catch (error) {
    ElNotification({
      type: "error",
      title: "Error",
      message: "데이터를 가져오는 동안 오류가 발생했습니다"
    });
  } finally {
    loading.value = false;
  }
};

const fetchStepExecutionByDate = async (start: string, end: string) => {
  loading.value = true;
  try {
    await stepExecutionStore.getStepExecutionByDate(parseInt(start), parseInt(end));
    dataList.value = stepExecutionStore.StepExecutionList;
  } catch (error) {
    ElNotification({
      type: "error",
      title: "Error",
      message: "데이터를 가져오는 동안 오류가 발생했습니다"
    });
  } finally {
    loading.value = false;
  }
};

const fetchStepExecutionByCustomEndpoint = async (endpoint: string) => {
  loading.value = true;
  try {
    await stepExecutionStore.getStepExecutionByCustomEndpoint(endpoint);
    dataList.value = stepExecutionStore.StepExecutionList;
  } catch (error) {
    ElNotification({
      type: "error",
      title: "Error",
      message: "데이터를 가져오는 동안 오류가 발생했습니다"
    });
  } finally {
    loading.value = false;
  }
};

const handleSearch = async () => {
  if (isSearchValid.value) {
    loading.value = true;
    try {
      let endpoint = '/step?';
      searchFields.value.forEach((field, index) => {
        if (field.selectedField === 'start') {
          if (field.startDate && field.endDate) {
            endpoint += `${index > 0 ? '&' : ''}start=${field.startDate}&end=${field.endDate}`;
          } else if (field.startDate) {
            endpoint += `${index > 0 ? '&' : ''}start=${field.startDate}`;
          } else if (field.endDate) {
            endpoint += `${index > 0 ? '&' : ''}end=${field.endDate}`;
          }
        } else {
          endpoint += `${index > 0 ? '&' : ''}${field.selectedField}=${field.query}`;
        }
      });
      await fetchStepExecutionByCustomEndpoint(endpoint);
      currentPage.value = 1;
    } catch (error) {
      ElNotification({
        type: "error",
        title: "Error",
        message: "데이터를 가져오는 동안 오류가 발생했습니다"
      });
    } finally {
      loading.value = false;
    }
  }
};

const addSearchField = () => {
  if (searchFields.value.length < 5) {
    searchFields.value.push({ selectedField: '', query: '', startDate: '', endDate: '' });
  }
};

const removeSearchField = (index: number) => {
  searchFields.value.splice(index, 1);
};

const handlePageChange = (page: number) => {
  currentPage.value = page;
};

const handleClick = (row: any, key: string) => {
  if (key === "exitMessage") {
    ElMessageBox.alert(row[key], `${key} Details`, {
      confirmButtonText: 'OK'
    });
  } else {
    if (key === 'stepExecutionId') {
      navigator.clipboard.writeText(row[key]).then(() => {
        ElNotification({
          type: 'success',
          title: 'Copied',
          message: `StepExecutionID: ${row[key]}가 복사되었습니다.`
        });
      }).catch(err => {
        console.error('Failed to copy: ', err);
        ElNotification({
          type: 'error',
          title: 'Copy Error',
          message: '복사에 실패했습니다.'
        });
      });
    } else if (key === 'stepName') {
      navigator.clipboard.writeText(row[key]).then(() => {
        ElNotification({
          type: 'success',
          title: 'Copied',
          message: `StepName: ${row[key]}가 복사되었습니다.`
        });
      }).catch(err => {
        console.error('Failed to copy: ', err);
        ElNotification({
          type: 'error',
          title: 'Copy Error',
          message: '복사에 실패했습니다.'
        });
      });
    } else if (key === 'jobExecutionId') {
      navigator.clipboard.writeText(row[key]).then(() => {
        ElNotification({
          type: 'success',
          title: 'Copied',
          message: `JobExecutionId: ${row[key]}가 복사되었습니다.`
        });
      }).catch(err => {
        console.error('Failed to copy: ', err);
        ElNotification({
          type: 'error',
          title: 'Copy Error',
          message: '복사에 실패했습니다.'
        });
      });
    } else if (key === 'startTime') {
      const date = new Date(row[key]);
      const formattedDate = date.toISOString().slice(2, 10).replace(/-/g, '');
      navigator.clipboard.writeText(formattedDate).then(() => {
        ElNotification({
          type: 'success',
          title: 'Copied',
          message: `startTime: ${formattedDate}가 복사되었습니다.`
        });
      }).catch(err => {
        console.error('Failed to copy: ', err);
        ElNotification({
          type: 'error',
          title: 'Copy Error',
          message: '복사에 실패했습니다.'
        });
      });
    } else if (key === 'status') {
      navigator.clipboard.writeText(row[key]).then(() => {
        ElNotification({
          type: 'success',
          title: 'Copied',
          message: `Status: ${row[key]}가 복사되었습니다.`
        });
      }).catch(err => {
        console.error('Failed to copy: ', err);
        ElNotification({
          type: 'error',
          title: 'Copy Error',
          message: '복사에 실패했습니다.'
        });
      });
    } else {
      emits('step-row-clicked', row);
      ElNotification({
        type: "info",
        title: "Info",
        message: `${row.stepExecutionId}번은 현재 ${row.status}상태 입니다.`
      });
    }
  }
};

const getCellClass = (key: string) => {
  const rightAlignedColumns = ["commitCount", "readCount", "filterCount", "writeCount", "readSkipCount", "writeSkipCount", "processSkipCount", "rollbackCount", "duration"];
  if (rightAlignedColumns.includes(key)) {
    return 'right-aligned';
  }
  if (key === "exitMessage") {
    return 'left-aligned';
  }
  return 'center-aligned';
};
</script>

<style scoped>
.el-table th {
  background-color: #f5f7fa;
  font-weight: bold;
  text-align: center;
}

.clickable-cell {
  cursor: pointer;
  color: inherit;
  text-decoration: none;
}

.center-aligned {
  text-align: center;
}

.right-aligned {
  text-align: right;
}

.left-aligned {
  text-align: left;
}

.highlight-cell {
  background-color: #a4e468;
}

.failed-cell {
  background-color: #ffcccc;
}

.button-color {
  background-color: #DCDCDC;
}

.search-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.search-select {
  width: 200px;
}

.search-input {
  width: 200px;
}

.search-switch {
  margin-right: 10px;
}
</style>
