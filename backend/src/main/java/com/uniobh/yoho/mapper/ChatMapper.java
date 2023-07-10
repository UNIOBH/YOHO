package com.uniobh.yoho.mapper;

import com.uniobh.yoho.pojo.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23249
* @description 针对表【t_chat】的数据库操作Mapper
* @createDate 2022-10-03 20:47:18
* @Entity com.uniobh.yoho.pojo.Chat
*/
@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

}




