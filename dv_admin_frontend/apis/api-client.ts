import axios from "axios";

type PropsType = string | object | Array<any> | null;

export const instance = axios.create({
  baseURL: "http://localhost:8100/batch",
  timeout: 60000,
});

export const reqGET = async (path: string, params: PropsType = null) =>
  await instance.request({ 
    method: "GET", 
    url: path, 
    params
  });

export const reqPOST = async (path: string, body: PropsType = null) =>
  await instance.request({
    method: "POST",
    url: path,
    data: JSON.stringify(body),
  });
