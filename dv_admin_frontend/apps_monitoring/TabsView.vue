<template>
  <div class="tabs-container">
    <el-tabs v-model="activeTab" type="card">
      <el-tab-pane label="InstanceVue" name="1">
        <InstanceVue @row-clicked="handleRowClicked" />
      </el-tab-pane>
      <el-tab-pane label="ExecutionVue" name="2">
        <ExecutionVue :selectedRow="selectedRow" :refresh="refresh" @step-row-clicked="handleStepRowClicked" />
      </el-tab-pane>
      <el-tab-pane label="StepExecutionVue" name="3">
        <StepExecutionVue :selectedRow="selectedStepRow" :refresh="stepRefresh" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import InstanceVue from './InstanceVue.vue';
import ExecutionVue from './ExecutionVue.vue';
import StepExecutionVue from './StepExecutionVue.vue';

const activeTab = ref('1');
const selectedRow = ref(null);
const selectedStepRow = ref(null);
const refresh = ref(false);
const stepRefresh = ref(false);

const handleRowClicked = (row) => {
  selectedRow.value = row;
  activeTab.value = '2';
  refresh.value = !refresh.value;
};

const handleStepRowClicked = (row) => {
  selectedStepRow.value = row;
  activeTab.value = '3';
  stepRefresh.value = !stepRefresh.value;
};
</script>

<style scoped>
.tabs-container {
  display: flex;
  justify-content: center;
  flex-direction: column;
}
</style>
