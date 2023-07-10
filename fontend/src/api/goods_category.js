/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-24 15:39:20
 * @LastEditTime: 2022-11-11 10:37:26
 */
import { request } from "./request";

/**
 * 获取所有分类
 */
export function getAll() {
  return request({
    url: "/goods-category",
    method: "get",
  });
}

/**
 * 添加类别
 * @param {String} name 类别名称
 */
export function addCategory(name) {
  return request({
    url: `/goods-category/${name}`,
    method: "post",
  });
}

/** =========================================
 * 根据id删除
 * @param {Number} id 类别id
 */
export function delCategory(id) {
  return request({
    url: `/goods-category/${id}`,
    method: "delete",
  });
}

/**
 *分页查询类别
 * @param {*} current
 * @param {*} size
 * @param {*} cName
 * @param {*} cId
 * @returns
 */
export function getCategoryPage(current, size, cName, cId) {
  return request({
    url: `/goods-category/${current}/${size}/${cName}/${cId}`,
    method: "get",
  });
}

/**
 * 根据id修改名称
 * @param {*} id 
 * @param {*} newName 
 * @returns 
 */
export function updateNameById(id, newName) {
  return request({
    url: `/goods-category/${id}/${newName}`,
    method: 'put',
  });
}
