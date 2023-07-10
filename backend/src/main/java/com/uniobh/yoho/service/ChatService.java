package com.uniobh.yoho.service;

import com.uniobh.yoho.pojo.Chat;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.ChatRecordsVo;
import com.uniobh.yoho.vo.GetChatVo;
import com.uniobh.yoho.vo.NewestChatVo;
import com.uniobh.yoho.vo.SendMsgVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author 23249
* @description 针对表【t_chat】的数据库操作Service
* @createDate 2022-10-03 20:47:18
*/
public interface ChatService extends IService<Chat> {

    ResultUtil<List<NewestChatVo>> getNewestChatByFromId(Integer fromId);

    ResultUtil<List<ChatRecordsVo>> getChatByToIdAndFromId(Integer toId, Integer fromId);

    ResultUtil<ChatRecordsVo> addChat(SendMsgVo sendMsgVo);

    ResultUtil<Boolean> addImg(MultipartFile img, Integer id);

    ResultUtil<NewestChatVo> getNewestChat(Integer fromId, Integer toId);
}
