package com.uniobh.yoho.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum RecommendEnum {
   YES(1, "推荐"), NO(0, "不推荐") ;
   @EnumValue
    private Integer recommendCode;
    private String recommendName;

    RecommendEnum(Integer recommendCode, String recommendName) {
        this.recommendCode = recommendCode;
        this.recommendName = recommendName;
    }
}
