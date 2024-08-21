export type StepExecutionList = StepExecutionItem[];
export type StepExecutionColumnList = TableColumn[];

export interface StepExecutionItem {
  stepExecutionId: number;
  stepName: string;
  jobExecutionId: number;
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
