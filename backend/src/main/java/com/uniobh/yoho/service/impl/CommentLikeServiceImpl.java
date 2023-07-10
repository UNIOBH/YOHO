package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.CommentLike;
import com.uniobh.yoho.service.CommentLikeService;
import com.uniobh.yoho.mapper.CommentLikeMapper;
import com.uniobh.yoho.utils.ResultUtil;
import org.springframework.stereotype.Service;

/**
* @author 23249
* @description 针对表【t_comment_like】的数据库操作Service实现
* @createDate 2022-11-11 17:25:31
*/
@Service
public class CommentLikeServiceImpl extends ServiceImpl<CommentLikeMapper, CommentLike>
    implements CommentLikeService{

    /**
     * 判断用户是否已经点过赞
     * @param cId 评论id
     * @param uId 用户id
     * @return
     */
    @Override
    public ResultUtil<Boolean> isLike(Integer cId, Integer uId) {
        LambdaQueryWrapper<CommentLike> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommentLike::getUId, uId).eq(CommentLike::getCId, cId);
        CommentLike commentLike = this.getOne(wrapper);
        if(commentLike == null) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), false);   // 未点赞
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), true);    // 已经点赞过
    }

    /**
     * 添加点赞记录
     * @param cId
     * @param uId
     * @return
     */
    @Override
    public ResultUtil<Boolean> addCommentLike(Integer cId, Integer uId) {
        CommentLike commentLike = new CommentLike();
        commentLike.setCId(cId);
        commentLike.setUId(uId);
        boolean save = this.save(commentLike);
        if(save) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), save);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), save);
    }
}




