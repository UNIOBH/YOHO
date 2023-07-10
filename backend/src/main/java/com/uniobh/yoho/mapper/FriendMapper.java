package com.uniobh.yoho.mapper;

import com.uniobh.yoho.pojo.Friend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23249
* @description 针对表【t_friend】的数据库操作Mapper
* @createDate 2022-10-03 20:50:54
* @Entity com.uniobh.yoho.pojo.Friend
*/
@Mapper
public interface FriendMapper extends BaseMapper<Friend> {

}




