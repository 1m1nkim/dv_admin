import { reqGET } from "../../../shared/apis/api-client";
import apiEndpoints from "../../../shared/apis/endpoints";

export class InstanceService {
  getInstanceList() {
    return reqGET(apiEndpoints.instance.all);
  }
}
