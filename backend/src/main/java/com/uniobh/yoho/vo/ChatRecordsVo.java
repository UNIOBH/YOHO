package com.uniobh.yoho.vo;

import com.uniobh.yoho.enums.ChatTypeEnum;
import com.uniobh.yoho.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRecordsVo {
    private Integer id;
    private String content; // 内容
    private ChatTypeEnum type;   // 消息类型
    private User to;    // 接收者
    private User from;  // 发送者
    private Date sendTime;  // 发送时间
}
