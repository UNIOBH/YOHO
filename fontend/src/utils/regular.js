/*
 * @Descripttion: 正则验证
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-10-18 16:19:25
 * @LastEditTime: 2022-11-03 17:57:31
 */
const emailReg = /^[1-9]\d{7,10}@qq\.(com)$/; // qq正则
const usernameReg = /^[a-zA-Z0-9_-]{4,16}$/; // 用户名正则
const passwordReg = /^[a-zA-Z0-9_-]{4,16}$/; // 密码正则
const nicknameReg = /^[\u4e00-\u9fa5a-zA-Z0-9]{3,10}$/; // 昵称正则

/**
 * 验证qq邮箱格式
 * @param {Reg} reg 正则表达式
 * @returns
 */
function email(reg) {
  return function (str) {
    return reg.test(str);
  };
}

/**
 * 验证用户名
 * @param {Reg} reg
 * @returns
 */
function username(reg) {
  return function (str) {
    return reg.test(str);
  };
}

/**
 * 验证密码
 * @param {Reg} reg
 * @returns
 */
function password(reg) {
  return function (str) {
    return reg.test(str);
  };
}

/**
 * 验证昵称
 * @param {Reg} reg
 * @returns
 */
function nickname(reg) {
  return function (str) {
    return reg.test(str);
  };
}

export const verifyEmail = email(emailReg);
export const verifyUsername = username(usernameReg);
export const verifyPassword = password(passwordReg);
export const verifyNickname = nickname(nicknameReg);
