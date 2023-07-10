package com.uniobh.yoho.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetChatVo {
    private Integer fromId; // 发送者id
    private Integer toId;   // 接收者id
    private String content; // 消息
}
