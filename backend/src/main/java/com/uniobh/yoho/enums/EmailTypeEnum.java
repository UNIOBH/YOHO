package com.uniobh.yoho.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTypeEnum {
    REGISTER("欢迎加入YoHo!!!", "register"),
    FORGETPASSWORD("YoHo——忘记密码", "forgetpassword"),
    ALTERPASSWORD("YoHo——修改密码", "alterpassword"),
    ;
    private String subject;    // 邮箱主题
    private String address;    // 邮箱模板引用
}
