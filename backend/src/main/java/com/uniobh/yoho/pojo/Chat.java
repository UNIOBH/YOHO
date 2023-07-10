package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.uniobh.yoho.enums.ChatTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_chat
 */
@TableName(value ="t_chat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat implements Serializable {
    /**
     * 聊天记录id
     */
    @TableId(type = IdType.AUTO, value = "h_id")
    private Integer id;

    /**
     * 消息内容
     */
    @TableField("h_content")
    private String content;

    /**
     * 消息类型
     */
    @TableField("h_type")
    private ChatTypeEnum type;

    /**
     * 发送时间
     */
    @TableField("h_send_time")
    private Date sendTime;

    /**
     * 发送者id（外键）
     */
    @TableField("h_sender_id")
    private Integer senderId;

    /**
     * 接收者id（外键）
     */
    @TableField("h_recipient_id")
    private Integer recipientId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}