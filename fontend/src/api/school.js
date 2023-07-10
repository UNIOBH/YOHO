/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-17 15:32:53
 * @LastEditTime: 2022-11-11 10:52:54
 */
import { request } from "./request";

/**
 * 根据城市id查询学校
 * @param {Number} cId 城市id
 */
export function getSchoolByCityId(cId) {
  return request({
    url: `/school/${cId}`,
    method: "get",
  });
}

/**
 * 用户向管理员提交添加学校申请
 * @param {Object} params
 */
export function addApply(params) {
  return request({
    url: "/apply",
    method: "post",
    params: params,
  });
}

/**
 * 添加学校
 * @param {Number} cId 城市id
 * @param {String} name 学校名称
 */
export function addSchool(cId, name) {
  return request({
    url: "/school",
    method: "post",
    data: {
      cId,
      name,
    },
  });
}

/**
 * 分页查询
 * @param {Number} current 当前页
 * @param {Number} num 一页多少条数据
 */
export function getSchoolByPage(current, num) {
  return request({
    url: `/school/${current}/${num}`,
    method: 'get',
  });
}

/**
 * 获取所有学校
 * @returns 
 */
export function getAll() {
  return request({
    url: `/school`,
    method: "get",
  });
}