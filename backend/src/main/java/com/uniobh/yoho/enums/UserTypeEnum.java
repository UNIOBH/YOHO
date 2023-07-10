package com.uniobh.yoho.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum UserTypeEnum {
    ADMIN(1, "管理员"), USER(0, "普通用户");
    @EnumValue
    private Integer type;
    private String typeName;

    UserTypeEnum(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }
}
