<template>
  <div class="flex flex-col gap-5 h-full">
    
    <el-table :data="dataList" style="width: 100%" max-height="800" border align="left">
      <el-table-column
        v-for="(column, index) in displayColumns"
        :key="column.key"
        :prop="column.key"
        :label="column.label"
        :width="column.width"
        :fixed="index === 0 ? 'left' : false"
        align="center"
        header-align="center"
      >
        <template v-slot="scope">
          <div @click="handleClick(scope.row)" class="clickable-cell">
            {{ scope.row[column.key] }}
          </div>
        </template>
      </el-table-column>
    </el-table>
    
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { InstanceService } from "../../../../service/batch/monitoring/instance";
import { ElNotification } from "element-plus";
import { defineEmits } from 'vue';

const emits = defineEmits(['row-clicked']);

const instanceService = new InstanceService();

const displayColumns = ref([
  { key: "jobInstanceId", label: "Job Instance ID", width: 150 },
  { key: "version", label: "Version", width: 100 },
  { key: "jobName", label: "Job Name", width: 250 },
  { key: "jobKey", label: "Job Key", width: 250 }
]);

const dataList = ref([]);

onMounted(() => {
  fetchData();
});

const fetchData = async () => {
  try {
    const result = await instanceService.getInstanceList();
    console.log(result.data);
    dataList.value = result.data;
  } catch (error) {
    console.error("데이터를 가져오는 동안 오류가 발생했습니다:", error);
    ElNotification({
      type: "error",
      title: "Error",
      message: "데이터를 가져오는 동안 오류가 발생했습니다"
    });
  }
};

const handleClick = (row: any) => {
  emits('row-clicked', row);
  ElNotification({
    type: "success",
    title: "Info",
    message: `${row.jobInstanceId}번 ExecutionVue로 이동합니다.`
  });
};
</script>

<style scoped>
  .el-table th {
    background-color: #f5f7fa;
    font-weight: bold;
  }

  .clickable-cell {
    cursor: pointer;
    color: inherit;
    text-decoration: none;
    text-align: center;
  }
</style>
