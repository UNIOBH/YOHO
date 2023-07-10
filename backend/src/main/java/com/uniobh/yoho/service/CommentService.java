package com.uniobh.yoho.service;

import com.uniobh.yoho.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddCommentVo;
import com.uniobh.yoho.vo.CommentVo;

import java.util.List;

/**
* @author 23249
* @description 针对表【t_comment】的数据库操作Service
* @createDate 2022-10-03 20:35:53
*/
public interface CommentService extends IService<Comment> {
    ResultUtil<List<CommentVo>> getCommentByIId(Integer IId);

    ResultUtil<Long> getCommentTotalByIId(Integer IId);

    ResultUtil<CommentVo> getCommentByToMId(Integer toMId);

    ResultUtil<Boolean> addCommentByAddCommentVo(AddCommentVo addCommentVo);

    ResultUtil<Boolean> addLike(Integer id, Integer uId);
}
