package com.uniobh.yoho.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniobh.yoho.enums.ChatTypeEnum;
import com.uniobh.yoho.vo.SendMsgVo;

/**
 * 发送消息工具类
 */
public class MessageUtil {
    public static final SendMsgVo getMsg(String msg) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SendMsgVo sendMsgVo = objectMapper.readValue(msg, SendMsgVo.class);
            return sendMsgVo;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
