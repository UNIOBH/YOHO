package com.uniobh.yoho.controller;

import com.uniobh.yoho.pojo.Chat;
import com.uniobh.yoho.service.ChatService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.ChatRecordsVo;
import com.uniobh.yoho.vo.GetChatVo;
import com.uniobh.yoho.vo.NewestChatVo;
import com.uniobh.yoho.vo.SendMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/chat/{fromId}")
    public ResultUtil<List<NewestChatVo>> getChatByGetChatVo(@PathVariable("fromId") Integer fromId) {
        return chatService.getNewestChatByFromId(fromId);
    }

    @GetMapping("/chat/record/{toId}/{fromId}")
    public ResultUtil<List<ChatRecordsVo>> getChatByToIdAndFromId(@PathVariable("toId") Integer toId,
                                                                  @PathVariable("fromId") Integer fromId) {
        return chatService.getChatByToIdAndFromId(toId, fromId);
    }
}
