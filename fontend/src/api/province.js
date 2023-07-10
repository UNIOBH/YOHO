/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-17 14:37:31
 * @LastEditTime: 2022-10-17 15:33:38
 */
import { request } from "./request";

/**
 * 查询所有省份
 * @returns
 */
export function getAll() {
  return request({
    url: "/province",
    method: "get",
  });
}
