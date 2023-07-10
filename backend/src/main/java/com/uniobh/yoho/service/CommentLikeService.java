package com.uniobh.yoho.service;

import com.uniobh.yoho.pojo.CommentLike;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;

/**
* @author 23249
* @description 针对表【t_comment_like】的数据库操作Service
* @createDate 2022-11-11 17:25:31
*/
public interface CommentLikeService extends IService<CommentLike> {
    ResultUtil<Boolean> isLike(Integer cId, Integer uId);

    ResultUtil<Boolean> addCommentLike(Integer cId, Integer uId);
}
