/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-11 17:33:18
 * @LastEditTime: 2022-11-16 15:29:50
 */
import axios from "axios";

export function request(config) {
  const instance = axios.create({
    baseURL: "/api",
    timeout: 20000,
  });

  instance.interceptors.request.use(
    (config) => {
      return config;
    },
    (err) => {
      console.log(err);
    }
  );

  instance.interceptors.response.use(
    (res) => {
      return res.data;
    },
    (err) => {
      console.log(err);
    }
  );

  return instance(config);
}
