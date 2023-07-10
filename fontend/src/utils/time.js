/*
 * @Descripttion:
 * @version: v1.0.0
 * @Author: uniobh
 * @Date: 2022-11-04 17:44:18
 * @LastEditTime: 2022-11-10 13:26:59
 */
/**
 * 截取时间字符串
 * @param {String} str 时间字符串
 */
export function intercept(str) {
  return str.substr(0, 10) + " " + str.substr(11, 8);
}

export function format(time) {
  return time.substr(0, 4) + "年" + time.substr(5, 2) + "月" + time.substr(8, 2) + "日";
}
