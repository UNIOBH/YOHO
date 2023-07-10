/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-21 11:19:44
 * @LastEditTime: 2022-11-11 14:37:31
 */
import { request } from "./request";

/**
 * 根据商品id查询商品详情
 * @param {Number} gId
 * @returns
 */
export function getGoodsById(gId) {
  return request({
    url: `/goods/${gId}`,
    method: "get",
  });
}

/**
 * 分页查询推荐商品
 * @param {Number} current 当前页数
 * @param {Number} num 一页多少条数据
 * @param {Number} sId 学校id
 * @returns
 */
export function getRecommendGoods(current, num, sId) {
  return request({
    url: `/goods/hot/${current}/${num}/${sId}`,
    method: "get",
  });
}

/**
 * 分页查询
 * @param {*} current
 * @param {*} num
 * @param {*} sId
 * @returns
 */
export function getGoodsByPage(current, num, sId) {
  return request({
    url: `/goods/${current}/${num}/${sId}`,
    method: "get",
  });
}

/**
 * 根据关键字分页查询
 * @param {Number} current 当前页
 * @param {Number} num 一页多少条数据
 * @param {String} keyword 查询关键字
 * @param {Number} sId 学校id
 * @param {Number} cId 类别id
 * @returns
 */
export function searchGoods(current, num, sId, cId, keyword) {
  return request({
    url: `/goods/${current}/${num}/${sId}/${cId}/${keyword}`,
    method: "get",
  });
}

/**
 * 管理员分页查询
 * @param {Number} current
 * @param {Number} num
 * @param {Number} isRecommend 是否为推荐
 * @param {Number} cId 类别id
 * @param {Number} gState 商品状态
 * @param {Number} uId 用户id
 * @returns
 */
export function adminSearchGoods(current, num, isRecommend, cId, gState, uId) {
  return request({
    url: `/goods/${current}/${num}/${isRecommend}/${cId}/${gState}/${uId}`,
    method: "get",
  });
}

/**
 * 增加商品点击量
 * @param {Number} gId 商品id
 */
export function addGoodsHits(gId) {
  return request({
    url: `/goods/hits/${gId}`,
    method: "put",
  });
}

/**
 * 修改推荐
 * @param {Number} recommend
 */
export function setRecomment(ids, recommend) {
  return request({
    url: `/goods/hot/${recommend}`,
    method: "put",
    data: ids,
  });
}

/**
 * 修改商品状态
 * @param {Array} ids 由商品id构成的集合
 * @param {Number} state 需要修改成的状态
 * @returns
 */
export function setState(ids, state) {
  return request({
    url: `/goods/state/${state}`,
    method: "put",
    data: ids,
  });
}

/**
 * 根据商品id查询所在类别
 * @param {Number} id 商品id
 */
export function getCategoryById(id) {
  return request({
    url: `/goods/category/${id}`,
    method: "get",
  });
}

/**
 * 添加商品
 * @param {Object} goods
 */
export function addGoods(goods) {
  return request({
    url: `/goods`,
    method: "post",
    data: goods,
  });
}

/**
 * 卖出商品
 * @param {Number} id 商品id
 * @param {Number} price 成交价格
 * @returns
 */
export function sellGoods(id, price) {
  return request({
    url: `/goods`,
    method: "put",
    data: {
      id: id,
      price: price,
    },
  });
}

/**
 * 根据用户id和商品状态查询商品
 * @param {Number} uId 用户id
 * @param {Number} state 状态
 * @returns
 */
export function getGoodsByUIdAndState(uId, state) {
  return request({
    url: `/goods/${uId}/${state}`,
    method: "get",
  });
}

/**
 * 修改商品所在类别
 * @param {*} cId 修改前的类别id
 * @param {*} afterCId 修改后的类别id
 * @returns
 */
export function updateCategoryByIds(cId, afterCId) {
  return request({
    url: `/goods/${cId}/${afterCId}`,
    method: "put",
  });
}

/**
 * 修改商品
 * @param {*} id 
 * @param {*} name 
 * @param {*} details 
 * @param {*} cId 
 * @param {*} price 
 * @returns 
 */
export function updateGoods(id, name, details, cId, price) {
  return request({
    url: `/goods/content`,
    method: 'put',
    data: {
      id,
      name,
      details,
      cId, 
      price
    }
  });
}
