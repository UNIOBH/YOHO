/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-11 17:42:10
 * @LastEditTime: 2022-11-17 09:30:38
 */
import { request } from "./request";

/**
 * 登录
 * @param {Object} user
 * @returns
 */
export function login(user) {
  return request({
    url: "/login",
    method: "post",
    data: user,
  });
}

/**
 * 注册
 * @param {Object} user 需要注册的用户信息
 */
export function register(user) {
  return request({
    url: "/register",
    method: "post",
    data: user,
  });
}

/**
 * 注册发送验证码
 * @param {Array} to 收件人
 * @returns
 */
export function sendCode(to) {
  return request({
    url: "/register-code",
    method: "post",
    data: to,
  });
}

/**
 * 忘记密码发送验证码
 * @param {Array} to 收件人
 * @returns
 */
export function sendForgetPassword(to) {
  return request({
    url: `/forget-password`,
    method: "post",
    data: to,
  });
}

/**
 * 检查邮箱是否存在
 * @param {String} email
 * @returns
 */
export function checkEmail(email) {
  return request({
    url: `/email/${email}`,
    method: "get",
  });
}

/**
 * 获取用户
 * @param {Number} id 用户id
 * @returns
 */
export function getUser(id) {
  return request({
    url: `/user/${id}`,
    method: "get",
  });
}

/**
 * 分页查询用户
 * @param {Number} current
 * @param {Number} size
 * @param {Number} uId
 * @param {Number} sId
 * @param {Number} sex
 * @param {String} keyword
 * @returns
 */
export function getUserPage(current, size, uId, sId, keyword, sex) {
  return request({
    url: `/user/${current}/${size}/${uId}/${sId}/${keyword}/${sex}`,
    method: "get",
  });
}

/**
 * 根据用户id删除用户
 * @param {*} id
 * @returns
 */
export function delUser(id) {
  return request({
    url: `/user/${id}`,
    method: "delete",
  });
}

/**
 *管理员注册
 * @param {*} username
 * @param {*} password
 * @returns
 */
export function adminRegister(username, password) {
  return request({
    url: `/admin`,
    method: "post",
    data: {
      username,
      password,
    },
  });
}

/**
 * 修改用户信息
 * @param {*} user
 * @returns
 */
export function updateUser(user) {
  return request({
    url: `/user`,
    method: "put",
    data: user,
  });
}

/**
 * 修改头像
 * @param {FormDate} formDate
 * @param {*} id
 * @returns
 */
export function updateAvatar(formDate, id) {
  return request({
    url: `/user/${id}`,
    method: "post",
    data: formDate,
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
}

/**
 * 根据邮箱修改密码
 * @param {*} email
 * @param {*} password
 * @returns
 */
export function updatePassword(email, password) {
  return request({
    url: `/user/${email}/${password}`,
    method: "put",
  });
}

/**
 * 根据用户id和旧密码修改密码
 * @param {Number} uId 用户id
 * @param {String} oldPsw 旧密码
 * @param {String} newPsw 新密码
 * @returns
 */
export function updatePswById(uId, oldPsw, newPsw) {
  return request({
    url: `/user/psw`,
    method: "put",
    data: {
      id: uId,
      oldPassword: oldPsw,
      newPassword: newPsw,
    },
  });
}
