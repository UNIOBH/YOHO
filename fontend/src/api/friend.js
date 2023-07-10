/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-11-04 13:55:31
 * @LastEditTime: 2022-11-17 10:30:01
 */
import { request } from "./request";

/**
 * 发送好友申请
 * @param {Object} params
 * @returns
 */
export function addFriend(params) {
  return request({
    url: "/friend",
    method: "post",
    data: params,
  });
}

/**
 * 根据用户id和关键字查找好友
 * @param {Number} uId 用户id
 * @param {String} keyword
 * @returns
 */
export function getFriendsByUId(uId, keyword) {
  return request({
    url: `/friend/${keyword}/${uId}`,
    method: "get",
  });
}

/**
 * 根据id删除
 * @param {Number} id 好友表id
 * @returns
 */
export function delFriendById(id) {
  return request({
    url: `/friend/${id}`,
    method: "delete",
  });
}

/**
 * 根据用户id查询正在验证的好友申请
 * @param {Number} uId
 * @returns
 */
export function getFriendsInVerificationByUId(uId) {
  return request({
    url: `/friend/${uId}`,
    method: "get",
  });
}

/**
 * 根据id修改state
 * @param {Number} id 好友表id
 * @param {Number} state 状态
 * @returns
 */
export function updateStateById(id, state) {
  return request({
    url: `/friend/${id}/${state}`,
    method: "put",
  });
}

/**
 * 判断双方是否为好友
 * @param {Number} uId
 * @param {Number} fId
 */
export function isFriend(uId, fId) {
  return request({
    url: `/friend/is/${uId}/${fId}`,
    method: "get",
  });
}

/**
 * 修改好友备注
 * @param {*} id
 * @param {*} remake
 * @returns
 */
export function updateRemake(id, remake) {
  return request({
    url: `/friend`,
    method: "put",
    data: {
      id,
      remake,
    },
  });
}
