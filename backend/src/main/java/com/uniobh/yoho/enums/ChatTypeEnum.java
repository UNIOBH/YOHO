package com.uniobh.yoho.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ChatTypeEnum {
    TEXT(1, "文本"), PICTURE(0, "图片");
    @EnumValue
    private Integer type;
    private String typeName;

    ChatTypeEnum(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }
}
