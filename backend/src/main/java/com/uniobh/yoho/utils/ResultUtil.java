package com.uniobh.yoho.utils;

import com.uniobh.yoho.enums.ResultStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status; // 状态码
    private String message; // 信息
    private T data; // 数据

    /**
     * <T>ResultUtil<T> 前面的<T>用来标识是泛型方法
     * @param <T>
     * @return
     */
    public static <T>ResultUtil<T> success() {
        return new ResultUtil<T>(ResultStatusEnum.PERFECT.getCode(), "操作成功", null);
    }

    public static <T>ResultUtil<T> success(Integer status) {
        return new ResultUtil<T>(status, "操作成功", null);
    }

    public static <T>ResultUtil<T> success(String message) {
        return new ResultUtil<T>(ResultStatusEnum.PERFECT.getCode(), message, null);
    }

    public static <T>ResultUtil<T> success(T data) {
        return new ResultUtil<T>(ResultStatusEnum.PERFECT.getCode(), "操作成功", data);
    }

    public static <T>ResultUtil<T> success(Integer status, String message) {
        return new ResultUtil<T>(status, message, null);
    }

    public static <T>ResultUtil<T> success(String message, T data) {
        return new ResultUtil<T>(ResultStatusEnum.PERFECT.getCode(), message, data);
    }

    public static <T>ResultUtil<T> success(Integer status, T data) {
        return new ResultUtil<T>(status, "操作成功", data);
    }

    public static <T>ResultUtil<T> success(Integer status, String message, T data) {
        return new ResultUtil<>(status, message, data);
    }

    public static <T>ResultUtil<T> error() {
        return new ResultUtil<T>(ResultStatusEnum.FAIL.getCode(), "操作失败", null);
    }

    public static <T>ResultUtil<T> error(String message) {
        return new ResultUtil<T>(ResultStatusEnum.FAIL.getCode(), message, null);
    }

    public static <T>ResultUtil<T> error(T data) {
        return new ResultUtil<T>(ResultStatusEnum.FAIL.getCode(), "操作失败", data);
    }

    public static <T>ResultUtil<T> error(Integer status) {
        return new ResultUtil<T>(status, "操作失败", null);
    }

    public static <T>ResultUtil<T> error(String message, T data) {
        return new ResultUtil<T>(ResultStatusEnum.FAIL.getCode(), message, data);
    }

    public static <T>ResultUtil<T> error(Integer status, T data) {
        return new ResultUtil<T>(status, "操作失败", data);
    }

    public static <T>ResultUtil<T> error(Integer status, String message) {
        return new ResultUtil<T>(status, message, null);
    }

    public static <T>ResultUtil<T> error(Integer status, String message, T data) {
        return new ResultUtil<T>(status, message, data);
    }
}
