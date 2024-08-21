export type ExecutionList = ExecutionItem[];
export type TableColumnList = TableColumn[];

export interface ExecutionItem {
  jobExecutionId: number;
  jobInstanceId: number;
  startTime: string;
  endTime: string;
  duration: number;
  status: string;
  exitCode: string;
  exitMessage: string;
}

export interface TableColumn {
  key: string;
  label: string;
  width: number;
}
