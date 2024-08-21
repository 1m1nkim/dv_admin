import { defineStore } from 'pinia';
import { ExecutionService } from '../../../service/batch/monitoring/execution';
import { ExecutionList } from '../../../types/batch/monitoring/execution';

const executionService = new ExecutionService();

export const useExecutionStore = defineStore("executionStore", {
  state: () => ({
    ExecutionList: [] as ExecutionList,
  }),
  actions: {
    async getInstanceExecutionById(id: string): Promise<ExecutionList> {
      const response = await executionService.getInstanceExecutionById(id);
      this.ExecutionList = response;
      return response;
    },
    async getExecutionByDate(start: number, end?: number): Promise<ExecutionList> {
      const response = await executionService.getExecutionByDate(start, end);
      this.ExecutionList = response;
      return response;
    },
    async getExecutionByCustomEndpoint(endpoint: string): Promise<ExecutionList> {
      const response = await executionService.getStepExecutionByCustomEndpoint(endpoint);
      this.ExecutionList = response;
      return response;
    }
   },
});
