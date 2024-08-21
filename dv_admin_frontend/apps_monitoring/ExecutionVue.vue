<template>
  <div class="flex flex-col gap-5 h-full" v-loading="loading">
    <div class="flex justify-between items-center">
      <div class="search-container flex-grow">
        <div class="flex items-center gap-2 mb-2"></div>
        <template v-if="!isMultiSearch">
          <div class="flex flex-col gap-2">
            <div class="flex items-center gap-2 mb-2">
              <el-button type="primary" @click="addSearchField">조건 추가</el-button>
            </div>
            <div v-for="(field, index) in searchFields" :key="index" class="flex items-center gap-2">
              <el-select v-model="field.selectedField" placeholder="검색 조건을 선택하세요." class="search-select">
                <el-option label="JobExecutionID" value="jobExecutionId"></el-option>
                <el-option label="JobInstanceID" value="jobInstanceId"></el-option>
                <el-option label="Start-End-Time" value="start-end-time"></el-option>
                <el-option label="Status" value="status"></el-option>
              </el-select>
              <el-input v-if="field.selectedField === 'start-end-time'" 
                v-model="field.startDate" 
                placeholder="Start Date: ex)240101" 
                class="search-input">
                <template #prefix>
                  <el-icon><Search /></el-icon>
                </template>
              </el-input>
              <el-input v-if="field.selectedField === 'start-end-time'" 
                v-model="field.endDate" 
                placeholder="End Date: ex)241231" 
                class="search-input">
                <template #prefix>
                  <el-icon><Search /></el-icon>
                </template>
              </el-input>
              <el-select v-if="field.selectedField === 'status'" 
                v-model="field.query" 
                placeholder="상태를 선택하세요" 
                class="search-input">
                <el-option label="COMPLETED" value="COMPLETED"></el-option>
                <el-option label="FAILED" value="FAILED"></el-option>
                <el-option label="STARTED" value="STARTED"></el-option>
              </el-select>
              <el-input v-else-if="field.selectedField !== 'start-end-time' && field.selectedField !== 'status'" 
                v-model="field.query" 
                placeholder="검색어를 입력하세요." 
                :disabled="!field.selectedField" 
                class="search-input">
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
        <el-button class="button-color" @click="handleRefresh">새로고침</el-button>
      </div>
    </div>

    <el-table :data="pagedData" style="width: 100%" max-height="800" border>
      <el-table-column v-for="(column, index) in displayColumns" 
        :key="column.key" :prop="column.key" 
        :label="column.label" 
        :width="column.width" 
        :fixed="index === 0 ? 'left' : false" 
        :align="getAlign(column.key)" 
        :header-align="'center'">
        <template #default="scope">
          <div @click="handleClick(scope.row, column.key)" 
            :class="['clickable-cell', { 'highlight-cell'
            : scope.row.status === 'STARTED', 'failed-cell'
            : scope.row.status === 'FAILED' }]">
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

    <Pagination :currentPage="currentPage" :pageSize="pageSize" :totalItems="totalItems" @update:currentPage="handlePageChange" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch, computed } from 'vue';
import { useExecutionStore } from '../../../../store/bath/monitoring/execution';
import { ElNotification, ElMessageBox } from 'element-plus';
import { defineProps, defineEmits } from 'vue';
import { TableColumn, ExecutionList } from '../../../../types/batch/monitoring/execution';
import { Search } from '@element-plus/icons-vue';
import Pagination from '../../../components/Pagination.vue';

const props = defineProps({
  selectedRow: Object
});

const emits = defineEmits(['step-row-clicked', 'loaded']);

const executionStore = useExecutionStore();

const displayColumns = ref<TableColumn[]>([
  { key: 'jobExecutionId', label: 'Job Execution ID', width: 180 },
  { key: 'jobInstanceId', label: 'Job Instance ID', width: 130 },
  { key: 'startTime', label: 'Start Time', width: 180 },
  { key: 'endTime', label: 'End Time', width: 180 },
  { key: 'duration', label: 'Duration(s)', width: 100 },
  { key: 'status', label: 'Status', width: 150 },
  { key: 'exitCode', label: 'Exit Code', width: 150 },
  { key: 'exitMessage', label: 'Exit Message', width: 150 }
]);

const dataList = ref<ExecutionList>([]);
const currentPage = ref(1);
const pageSize = ref(16);
const totalItems = computed(() => dataList.value.length);
const loading = ref(false);

const isMultiSearch = ref(false);
const searchFields = ref([{ selectedField: '', query: '', startDate: '', endDate: '' }]);

const isSearchValid = computed(() => {
  return searchFields.value.every(field => {
    if (field.selectedField === 'start-end-time') {
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

onMounted(() => {
  fetchData();
});

watch(() => props.selectedRow, (newRow) => {
  if (newRow) {
    fetchInstanceExecutionById(newRow.jobInstanceId);
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
    await fetchExecutionByDate(startDate, endDate);
  } catch (error) {
    ElNotification({
      type: 'error',
      title: 'Error',
      message: '데이터를 가져오는 동안 오류가 발생했습니다'
    });
  } finally {
    loading.value = false;
  }
};

const fetchExecutionByDate = async (start: string, end: string) => {
  loading.value = true;
  try {
    await executionStore.getExecutionByDate(Number(start), Number(end));
    dataList.value = executionStore.ExecutionList;
  } catch (error) {
    ElNotification({
      type: 'error',
      title: 'Error',
      message: '데이터를 가져오는 동안 오류가 발생했습니다'
    });
  } finally {
    loading.value = false;
  }
};

const fetchInstanceExecutionById = async (id: string) => {
  loading.value = true;
  try {
    await executionStore.getInstanceExecutionById(id);
    dataList.value = executionStore.ExecutionList;
    emits('loaded', 'execution');
  } catch (error) {
    ElNotification({
      type: 'error',
      title: 'Error',
      message: '데이터를 가져오는 동안 오류가 발생했습니다'
    });
  } finally {
    loading.value = false;
  }
};

const fetchExecutionByCustomEndpoint = async (endpoint: string) => {
  loading.value = true;
  try {
    await executionStore.getExecutionByCustomEndpoint(endpoint);
    dataList.value = executionStore.ExecutionList;
  } catch (error) {
    ElNotification({
      type: 'error',
      title: 'Error',
      message: '데이터를 가져오는 동안 오류가 발생했습니다'
    });
  } finally {
    loading.value = false;
  }
};

const handleSearch = async () => {
  if (isSearchValid.value) {
    loading.value = true;
    try {
      let endpoint = '/execution?';
      searchFields.value.forEach((field, index) => {
        if (field.selectedField === 'start-end-time') {
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
      await fetchExecutionByCustomEndpoint(endpoint);
      currentPage.value = 1;
    } catch (error) {
      ElNotification({
        type: 'error',
        title: 'Error',
        message: '데이터를 가져오는 동안 오류가 발생했습니다'
      });
    } finally {
      loading.value = false;
    }
  }
};

const addSearchField = () => {
  if (searchFields.value.length < 4) {
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
  if (key === 'exitMessage') {
    ElMessageBox.alert(row[key], `${key} Details`, {
      confirmButtonText: 'OK'
    });
  } else if (key === 'jobInstanceId') {
    navigator.clipboard.writeText(row[key]).then(() => {
      ElNotification({
        type: 'success',
        title: 'Copied',
        message: `JobInstanceId: ${row[key]}가 복사되었습니다.`
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
  } else if (key === 'endTime') {
    const date = new Date(row[key]);
    const formattedDate = date.toISOString().slice(2, 10).replace(/-/g, '');
    navigator.clipboard.writeText(formattedDate).then(() => {
      ElNotification({
        type: 'success',
        title: 'Copied',
        message: `endTime: ${formattedDate}가 복사되었습니다.`
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
        message: `status: ${row[key]}가 복사되었습니다.`
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
      type: 'info',
      title: 'Info',
      message: `${row.jobExecutionId}번은 현재 ${row.status}상태 입니다.`
    });
  }
};

const getAlign = (key: string) => {
  const centerAlignedColumns = ['jobExecutionId', 'jobInstanceId', 'startTime', 'endTime', 'status', 'exitCode'];
  if (centerAlignedColumns.includes(key)) {
    return 'center';
  }
  if (key === 'duration') {
    return 'right';
  }
  return 'left';
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

.mb-2 {
  margin-bottom: 8px;
}
</style>
