package com.uniobh.yoho.mapper;

import com.uniobh.yoho.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23249
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-10-03 21:13:34
* @Entity com.uniobh.yoho.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




