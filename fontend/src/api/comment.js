/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-11-08 13:11:46
 * @LastEditTime: 2022-11-11 19:54:43
 */
import { request } from "./request";

/**
 * 添加评论
 * @param {Object} data
 * @returns
 */
export function addComment(data) {
  return request({
    url: `/comment`,
    method: "post",
    data: data,
  });
}

/**
 * 增加点赞数
 * @param {*} id
 * @param {*} uId
 * @returns
 */
export function addCommentLike(id, uId) {
  return request({
    url: `/comment/${id}/${uId}`,
    method: "put",
  });
}
