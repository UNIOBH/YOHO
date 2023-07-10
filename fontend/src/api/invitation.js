/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-11-03 20:47:36
 * @LastEditTime: 2022-11-11 20:21:41
 */
import { request } from "./request";

/**
 * 分页查询推荐帖子
 * @param {Number} current 当前页数
 * @param {Number} num 一页多少条数据
 * @param {Number} sId 学校id
 * @returns
 */
export function getRecommendInvitation(current, num, sId) {
  return request({
    url: `/invitation/${current}/${num}/${sId}`,
    method: "get",
  });
}

/**
 * 根据关键字分页查询
 * @param {Number} current
 * @param {Number} num
 * @param {Number} sId
 * @param {String} keyword 关键字
 */
export function getInvitation(current, num, sId, keyword) {
  return request({
    url: `/invitation/${current}/${num}/${keyword}/${sId}`,
    method: "get",
  });
}

/**
 * 管理员查询
 * @param {Number} current
 * @param {Number} num
 * @param {Number} recommend
 * @param {String} keyword
 * @param {Number} uId
 */
export function adminSearchInvitation(current, num, recommend, keyword, uId) {
  return request({
    url: `/invitation/${current}/${num}/${recommend}/${keyword}/${uId}`,
    method: "get",
  });
}

/**
 * 根据帖子id查询帖子详情
 * @param {Number} iId 帖子id
 */
export function getInvitationById(iId) {
  return request({
    url: `/invitation/${iId}`,
    method: "get",
  });
}

/**
 * 删除帖子
 * @param {Array} ids 帖子id
 * @returns
 */
export function del(ids) {
  return request({
    url: `/invitation`,
    method: "delete",
    data: ids,
  });
}

/**
 * 修改帖子是否推荐
 * @param {Array} ids
 * @param {Number} recommend
 */
export function setRecommed(ids, recommend) {
  return request({
    url: `/invitation/${recommend}`,
    method: "put",
    data: ids,
  });
}

/**
 * 增加帖子阅读量
 * @param {Number} id
 */
export function addReadCount(id) {
  return request({
    url: "/invitation",
    method: "put",
    data: id,
    headers: {
      "Content-Type": "application/json",
    },
  });
}

/**
 * 根据用户id查询
 * @param {Number} uId 用户id
 * @returns
 */
export function getInvitatonsByUId(uId) {
  return request({
    url: `/invitation/profile/${uId}`,
    method: "get",
  });
}

/**
 * 添加帖子
 * @param {String} title 帖子标题
 * @param {String} details 帖子详情
 * @param {Number} uId 用户id
 * @returns
 */
export function addInvitation(title, details, uId) {
  return request({
    url: `/invitation`,
    method: "post",
    data: {
      title,
      details,
      uId,
    },
  });
}

/**
 * 根据id获取粗略的信息
 * @param {*} id
 */
export function getRoughInvitation(id) {
  return request({
    url: `/invitation/rough/${id}`,
    method: "get",
  });
}

export function updateContentById(id, title, content) {
  return request({
    url: `/invitation/content`,
    method: "put",
    data: {
      id,
      title,
      content,
    },
  });
}

/**
 * 添加点击量
 * @param {*} id 
 * @param {*} uId 
 * @returns 
 */
export function addLike(id, uId) {
  return request({
    url: `/invitation/${id}/${uId}`,
    method: 'put'
  });
}

/**
 * 根据用户id获取用户喜欢的帖子
 * @param {*} uId 
 * @returns 
 */
export function getLikeInvitaionByUId(uId) {
  return request({
    url: `/invitation/favor/${uId}`,
    method: 'get',
  });
}