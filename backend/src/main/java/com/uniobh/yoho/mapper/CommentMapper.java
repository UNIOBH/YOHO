package com.uniobh.yoho.mapper;

import com.uniobh.yoho.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23249
* @description 针对表【t_comment】的数据库操作Mapper
* @createDate 2022-10-03 20:35:53
* @Entity com.uniobh.yoho.pojo.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




