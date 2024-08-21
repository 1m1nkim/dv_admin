import { defineStore } from 'pinia';
import { InstanceService } from '../../../service/batch/monitoring/instance';
import { InstanceItem, InstanceList } from '../../../types/batch/monitoring/instance';

const instanceService = new InstanceService();

export const useInstanceStore = defineStore("instanceStore", {
  state: () => ({
    InstanceList: [] as InstanceList,
  }),
  actions: {
    async getInstanceList(): Promise<InstanceItem[]> {
      const response = await instanceService.getInstanceList();
      this.InstanceList = response.data;
      return response.data;
    },
  },
});
