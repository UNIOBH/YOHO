/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-17 14:58:47
 * @LastEditTime: 2022-10-17 15:04:32
 */
import { request } from "./request";

/**
 * 根据省份id查询城市
 * @param {Number} pId 省份id
 */
export function getCityByProvinceId(pId) {
  return request({
    url: `/city/${pId}`,
    method: "get",
  });
}
