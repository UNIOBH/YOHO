package com.uniobh.yoho.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ApplyStateEnum {
    AGREE(1, "同意"),
    REFUSE(0, "拒绝"),
    UNHANDLED(2, "未处理");
    @EnumValue
    private Integer code;
    private String msg;

    ApplyStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
