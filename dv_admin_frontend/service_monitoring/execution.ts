import { reqGET } from "../../../shared/apis/api-client";
import { ExecutionList } from "../../../types/batch/monitoring/execution";
import endpoint from "../../../shared/apis/endpoints";

export class ExecutionService {
  async getInstanceExecutionById(id: string): Promise<ExecutionList> {
    const response = await reqGET(endpoint.execution.byJobInstanceId(id));
    return response.data as ExecutionList;
  }

  async getExecutionByDate(start: number, end?: number): Promise<ExecutionList> {
    const response = await reqGET(endpoint.execution.byDateRange(start, end));
    return response.data as ExecutionList;
  }

  async getStepExecutionByCustomEndpoint(endpoint: string): Promise<ExecutionList> {
    const response = await reqGET(endpoint);
    return response.data as ExecutionList;
  }
}