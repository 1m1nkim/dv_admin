import { createRouter, createWebHistory } from "vue-router";

import Home from "../views/apps/sample/Home.vue";
import About from "../views/apps/sample/About.vue";
import TableGrid from "../views/apps/sample/TableGrid.vue";
import TabsView from "../views/apps/batch/monitoring/TabsView.vue";
import InstanceVue from "../views/apps/batch/monitoring/InstanceVue.vue";
import ExecutionVue from "../views/apps/batch/monitoring/ExecutionVue.vue";
import StepExecutionVue from "../views/apps/batch/monitoring/StepExecutionVue.vue";
import Paging from "../views/components/Pagination.vue";
import NotFound from "../views/pages/NotFound.vue";
import { pa } from "element-plus/es/locales.mjs";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    component: About,
  },
  {
    path: "/table-grid",
    name: "Table",
    component: TableGrid,
  },
  {
    path: "/:pathMatch(.*)*",
    name: "not-found",
    component: NotFound,
  },
  {
    path: "/tabs-View",
    name: "TabsView",
    component: TabsView,
  },
  {
    path: "/instance-vue",
    name: "InstanceVue",
    component: InstanceVue,
  },
  {
    path: "/execution-vue",
    name: "ExecutionVue",
    component: ExecutionVue,
  },
  {
    path: "/step-execution-vue",
    name: "StepExecutionVue",
    component: StepExecutionVue,
  }
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
