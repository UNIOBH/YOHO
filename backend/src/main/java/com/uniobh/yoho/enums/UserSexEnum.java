package com.uniobh.yoho.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum UserSexEnum {
    MALE(1, "男"), FEMALE(0, "女");
    @EnumValue
    private Integer sex;
    private String sexName;

    UserSexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
