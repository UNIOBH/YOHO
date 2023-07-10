package com.uniobh.yoho.enums;

import lombok.Getter;

@Getter
public enum ResultStatusEnum {
    PERFECT(200),   // 成功
    NO_DATA(201),   // 无数据
    FAIL(400),  // 失败
    EXIST(202)  // 已存在
    ;
    private Integer code;

    ResultStatusEnum(Integer code) {
        this.code = code;
    }
}
