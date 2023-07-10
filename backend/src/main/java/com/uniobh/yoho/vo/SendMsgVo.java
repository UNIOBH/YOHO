package com.uniobh.yoho.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uniobh.yoho.enums.ChatTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMsgVo {
    @JsonProperty("content")
    private String content; // 内容
    @JsonProperty("type")
    private Integer type;  // 消息类型
    @JsonProperty("fromId")
    private Integer fromId; // 发送者id
    @JsonProperty("toId")
    private Integer toId;   // 接受者id
}
