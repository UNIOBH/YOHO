package com.uniobh.yoho.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum FriendStateEnum {
    VERIFYING(0, "验证中"), ALREADY(1, "已是好友"), REFUSE(2, "拒绝");
    @EnumValue
    private Integer state;
    private String stateName;
}
