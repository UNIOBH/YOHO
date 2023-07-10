/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-11-09 18:55:09
 * @LastEditTime: 2022-11-10 11:24:10
 */
import { request } from "./request";

/**
 * 根据接受者id和发送者id查询聊天记录
 * @param {Number} toId 接受者id
 * @param {Number} fromId 发送者id
 */
export function getChatRecord(toId, fromId) {
  return request({
    url: `/chat/record/${toId}/${fromId}`,
    method: "get",
  });
}

/**
 * 查询最新一条聊天记录
 * @param {Number} fromId
 * @returns
 */
export function getNewestChat(fromId) {
  return request({
    url: `/chat/${fromId}`,
    method: "get",
  });
}
