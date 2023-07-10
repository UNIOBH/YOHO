package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.ChatTypeEnum;
import com.uniobh.yoho.enums.FileTypeEnum;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.Chat;
import com.uniobh.yoho.pojo.User;
import com.uniobh.yoho.service.ChatService;
import com.uniobh.yoho.mapper.ChatMapper;
import com.uniobh.yoho.service.FriendService;
import com.uniobh.yoho.service.UserService;
import com.uniobh.yoho.utils.ImageUtil;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
* @author 23249
* @description 针对表【t_chat】的数据库操作Service实现
* @createDate 2022-10-03 20:47:18
*/
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat>
    implements ChatService{

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    /**
     * 根据用户id查询和好友最新的一条聊天记录
     * @param fromId
     * @return
     */
    @Override
    public ResultUtil<List<NewestChatVo>> getNewestChatByFromId(Integer fromId) {
        List<GetFriendVo> friendVoList = friendService.getFriendByUId(fromId, null).getData();
        if(friendVoList.size() == 0 || friendVoList == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        ArrayList<NewestChatVo> res = new ArrayList<>();
        for (GetFriendVo friendVo : friendVoList) {
            NewestChatVo newestChatVo = getNewestChat(fromId, friendVo.getFriend().getId()).getData();
            if(newestChatVo != null) res.add(newestChatVo);
        }
        if(res.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 根据发送者id和接收者id按时间排序查询
     * @param toId 接收者id
     * @param fromId 发送者id
     * @return
     */
    @Override
    public ResultUtil<List<ChatRecordsVo>> getChatByToIdAndFromId(Integer toId, Integer fromId) {
        LambdaQueryWrapper<Chat> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(toId != 0 && toId != null && fromId != 0 && fromId != null,
                i -> i.eq(Chat::getRecipientId, toId)
                        .or()
                        .eq(Chat::getRecipientId, fromId)
                        .or()
                        .eq(Chat::getSenderId, toId)
                        .or()
                        .eq(Chat::getSenderId, fromId))
                .orderByAsc(Chat::getSendTime);
        List<Chat> list = this.list(wrapper);
        if(list == null || list.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无聊天记录");
        ArrayList<ChatRecordsVo> res = new ArrayList<>();
        for (Chat chat : list) {
            ChatRecordsVo recordsVo = new ChatRecordsVo();
            recordsVo.setId(chat.getId());
            recordsVo.setContent(chat.getContent());
            recordsVo.setType(chat.getType());
            // 发送者
            User u1 = userService.getUserById(chat.getSenderId()).getData();
            if(u1 != null) {
                User user = new User();
                user.setAvatar(u1.getAvatar());
                user.setId(u1.getId());
                recordsVo.setFrom(user);
            }
            // 接收者
            User u2 = userService.getUserById(chat.getRecipientId()).getData();
            if (u2 != null) {
                User user = new User();
                user.setAvatar(u2.getAvatar());
                user.setId(u2.getId());
                recordsVo.setTo(user);
            }
            res.add(recordsVo);
        }
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    @Override
    public ResultUtil<ChatRecordsVo> addChat(SendMsgVo sendMsgVo) {
        if(sendMsgVo == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        Chat chat = new Chat();
        ChatTypeEnum chatTypeEnum = null;
        if(sendMsgVo.getType() == ChatTypeEnum.TEXT.getType()) chatTypeEnum = ChatTypeEnum.TEXT;
        else if(sendMsgVo.getType() == ChatTypeEnum.PICTURE.getType()) chatTypeEnum = ChatTypeEnum.PICTURE;
        chat.setContent(sendMsgVo.getContent());
        chat.setType(chatTypeEnum);
        chat.setSenderId(sendMsgVo.getFromId());
        chat.setRecipientId(sendMsgVo.getToId());
        boolean save = this.save(chat); // 添加聊天记录
        if(!save) return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
        ChatRecordsVo recordsVo = new ChatRecordsVo();
        recordsVo.setContent(chat.getContent());
        recordsVo.setId(chat.getId());
        recordsVo.setType(chat.getType());
        // 发送者
        User u1 = userService.getUserById(chat.getSenderId()).getData();
        if(u1 != null) {
            User user = new User();
            user.setAvatar(u1.getAvatar());
            user.setId(u1.getId());
            recordsVo.setFrom(user);
        }
        // 接收者
        User u2 = userService.getUserById(chat.getRecipientId()).getData();
        if (u2 != null) {
            User user = new User();
            user.setAvatar(u2.getAvatar());
            user.setId(u2.getId());
            recordsVo.setTo(user);
        }
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), recordsVo);
    }

    /** ====================================================================
     * 发送图片
     * @param img
     * @param id 用户id
     * @return
     */
    @Override
    public ResultUtil<Boolean> addImg(MultipartFile img, Integer id) {
        MultipartFile[] file = {img};
        List<String> list = ImageUtil.upload(file, FileTypeEnum.CHAT, id);
        if(list != null && list.size() != 0) {
            Chat chat = new Chat();
            chat.setType(ChatTypeEnum.PICTURE);
//            this.save()
        }
        return null;
    }

    /**
     * 根据fromId和toId查询最新的聊天记录
     * @param fromId
     * @param toId
     * @return
     */
    @Override
    public ResultUtil<NewestChatVo> getNewestChat(Integer fromId, Integer toId) {
        LambdaQueryWrapper<Chat> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(toId != 0 && toId != null && fromId != 0 && fromId != null,
                i -> i.eq(Chat::getRecipientId, toId)
                        .or()
                        .eq(Chat::getRecipientId, fromId)
                        .or()
                        .eq(Chat::getSenderId, toId)
                        .or()
                        .eq(Chat::getSenderId, fromId))
                .orderByDesc(Chat::getSendTime)
                .last("limit 1");
        Chat chat = this.getOne(wrapper);
        if(chat == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        NewestChatVo vo = new NewestChatVo();
        vo.setId(chat.getId());
        vo.setContent(chat.getContent());
        vo.setType(chat.getType());
        vo.setTime(chat.getSendTime());
        // 接收者
        User u = userService.getUserById(chat.getRecipientId()).getData();
        if (u != null) {
            User user = new User();
            user.setAvatar(u.getAvatar());
            user.setId(u.getId());
            user.setNickname(u.getNickname());
            vo.setTo(user);
        }
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), vo);
    }
}




