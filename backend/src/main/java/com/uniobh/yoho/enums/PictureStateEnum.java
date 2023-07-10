package com.uniobh.yoho.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum PictureStateEnum {
    VIOLATION(1, "违规"), QUALIFIED(0, "合格");
    @EnumValue
    private Integer state;
    private String stateName;

    PictureStateEnum(Integer state, String stateName) {
        this.state = state;
        this.stateName = stateName;
    }
}