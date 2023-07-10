/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-11-07 18:23:37
 * @LastEditTime: 2022-11-08 09:39:18
 */
import { request } from "./request";

/**
 * 上传主图
 * @param {FormDate} formDate
 * @param {Number} uId
 * @param {Number} gId
 * @returns
 */
export function uploadMainImg(formDate, uId, gId) {
  return request({
    url: `/goods-picture/main/${uId}/${gId}`,
    method: "post",
    data: formDate,
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
}

/**
 * 上传商品其他的图片
 * @param {} formDate
 * @param {*} uId
 * @param {*} gId
 * @returns
 */
export function uploadImgs(formDate, uId, gId) {
  return request({
    url: `/goods-picture/${uId}/${gId}`,
    method: "post",
    data: formDate,
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
}

/**
 * 根据商品id获取图片
 * @param {*} gId 商品id
 * @returns
 */
export function getImgsByGId(gId) {
  return request({
    url: `/goods-picture/${gId}`,
    method: "get",
  });
}

/**
 * 设置违规图片
 * @param {Number} id 
 * @returns 
 */
export function setViolation(id) {
  return request({
    url: `/goods-picture/${id}`,
    method: 'put',
  });
}
