import { defineStore } from 'pinia';
import { StepExecutionService } from '../../../service/batch/monitoring/stepExecution';
import { StepExecutionList } from '../../../types/batch/monitoring/stepExecution';

const stepExecutionService = new StepExecutionService();

export const useStepExecutionStore = defineStore("stepExecutionStore", {
  state: () => ({
    StepExecutionList: [] as StepExecutionList,
  }),
  actions: {
    async getExecutionStepExecutionById(id: string): Promise<StepExecutionList> {
      const response = await stepExecutionService.getExecutionsStepExecutionById(id);
      this.StepExecutionList = response;
      return response;
    },
    async getStepExecutionByDate(start: number, end?: number): Promise<StepExecutionList> {
      const response = await stepExecutionService.getStepExecutionByDate(start, end);
      this.StepExecutionList = response;
      return response;
    },
    async getStepExecutionByCustomEndpoint(endpoint: string): Promise<StepExecutionList> {
      const response = await stepExecutionService.getStepExecutionByCustomEndpoint(endpoint);
      this.StepExecutionList = response;
      return response;
    }
  },
});
