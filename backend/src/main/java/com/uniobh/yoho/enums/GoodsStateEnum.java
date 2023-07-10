package com.uniobh.yoho.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum GoodsStateEnum {
    RELEASE(1, "发布"), SOLD(2, "已出售"), BAN(3, "下架");
    @EnumValue
    private Integer state;
    private String stateName;

    GoodsStateEnum(Integer state, String stateName) {
        this.state = state;
        this.stateName = stateName;
    }
}
