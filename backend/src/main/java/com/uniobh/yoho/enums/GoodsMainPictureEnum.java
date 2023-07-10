package com.uniobh.yoho.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum GoodsMainPictureEnum {
    YES(1, "主图"), NO(2, "不是主图");
    @EnumValue
    private Integer code;
    private String name;

    GoodsMainPictureEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
