export default {
  sample: {
    todos: "/",
  },
  instance: {
    all: "/instance/all",
  },
  execution: {
    all: "/execution/all",
    byJobInstanceId: (id: string) => `/instance/execution?jobInstanceId=${id}`,
    byJobInstanceExecutionId: (id: string) => `/execution?jobInstanceId=${id}`,
    byDateRange: (start: number, end?: number) => end ? `/execution?start=${start}&end=${end}` : `/step?start=${start}`
  },
  stepExecution: {
    all: "/step/all",
    byJobExecutionId: (id: string) => `/execution/step?jobExecutionId=${id}`,
    byDateRange: (start: number, end?: number) => end ? `/step?start=${start}&end=${end}` : `/step?start=${start}`,
  }
};
