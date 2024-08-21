export type InstanceList = InstanceItem[];
export type TableColumnList = TableColumn[];

export interface InstanceItem {
  jobInstanceId: number;
  version: number;
  jobName: string;
  jobKey: string;
}

export interface TableColumn {
  key: string;
  label: string;
  width: number;
}
