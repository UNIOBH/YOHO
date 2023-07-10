package com.uniobh.yoho.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniobh.yoho.config.WebSocketConfig;
import com.uniobh.yoho.service.ChatService;
import com.uniobh.yoho.utils.MessageUtil;
import com.uniobh.yoho.vo.ChatRecordsVo;
import com.uniobh.yoho.vo.SendMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * websocket
 */
@Component
@ServerEndpoint(value = "/chat/{uId}")
@Slf4j
//@ConditionalOnClass(WebSocketConfig.class)
public class WebSocket {

    private static ChatService chatService;

    private static Map<Integer, WebSocket> onLineUsers = new HashMap<>();   // 存储在线用户

    private Session session;    // 存储当前创建连接的session

    private Integer uId;    // 当前用户id

    /**
     * 因为spring默认对bean的管理是单列模式，而websocket是多对象，两者相冲突
     * 在项目初始化的时候，spring会注入service，但是由于spring是单例模式，所以只会注入一次
     * 当有新的websocket被创建时，spring不会再注入service，在后续的调用时会报空指针异常
     *
     * 解决办法：将service设为静态的，这样不管有多少个websocket对象也只有一个service
     * @param chatService
     */
    @Autowired
    private void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("uId") Integer uId) {   // 连接建立时调用
        log.info("-----------------------创建连接成功:"+ uId +"-----------------------");
        this.uId = uId;
        onLineUsers.put(uId, this); // 存储当前用户
        this.session = session;
    }

    /**
     * 客户端向服务端发送请求时调用
     * @param session
     * @param msg 前端发送的信息
     */
    @OnMessage
    public void onMessage(Session session, String msg) {
        try {
            SendMsgVo sendMsgVo = MessageUtil.getMsg(msg);
            if(sendMsgVo != null) sendMsgVo.setFromId(this.uId);
            WebSocket webSocket = onLineUsers.get(sendMsgVo.getToId()); // 获取消息接收者
            // 接收者不在线，直接把数据存入数据库
            ChatRecordsVo recordsVo = chatService.addChat(sendMsgVo).getData();
            ObjectMapper objectMapper = new ObjectMapper();
            if(webSocket != null)  webSocket.session.getBasicRemote().sendText(objectMapper.writeValueAsString(recordsVo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) { // 连接关闭时调用
        log.info("-----------------------连接关闭" + this.uId + "-----------------------");
        onLineUsers.remove(this.uId);
    }

}
