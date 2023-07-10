package com.uniobh.yoho.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFriendVo {
    @JsonProperty("uId")
    private Integer uId;    // 用户id
    @JsonProperty("fUId")
    private Integer fUId;   // 添加好友id
    @JsonProperty("msg")
    private String msg; // 验证信息
}
