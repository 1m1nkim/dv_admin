import { reqGET } from "../../../shared/apis/api-client";
import { StepExecutionList } from "../../../types/batch/monitoring/stepExecution";
import endpoint from "../../../shared/apis/endpoints";

export class StepExecutionService {
  async getExecutionsStepExecutionById(id: string): Promise<StepExecutionList> {
    const response = await reqGET(endpoint.stepExecution.byJobExecutionId(id));
    return response.data;
  }

  async getStepExecutionByDate(start: number, end?: number): Promise<StepExecutionList> {
    const response = await reqGET(endpoint.stepExecution.byDateRange(start, end));
    return response.data as StepExecutionList;
  }

  async getStepExecutionByCustomEndpoint(endpoint: string): Promise<StepExecutionList> {
    const response = await reqGET(endpoint);
    return response.data as StepExecutionList;
  }
}
