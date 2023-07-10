/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-11-05 21:17:26
 * @LastEditTime: 2022-11-05 21:40:12
 */
import { request } from "./request";

/**
 * 分页查询未处理的申请
 * @param {Number} current
 * @param {Number} num
 * @returns
 */
export function getApply(current, num) {
  return request({
    url: `/apply/${current}/${num}`,
    method: "get",
  });
}

/**
 * 修改申请状态
 * @param {Number} cId 城市id
 * @param {Number} id 申请id
 * @param {String} name 学校名称
 * @param {Number} state 状态
 * @returns
 */
export function setState(cId, id, name, state) {
  return request({
    url: `/apply/${cId}/${id}/${name}/${state}`,
    method: 'put',
  });
}
