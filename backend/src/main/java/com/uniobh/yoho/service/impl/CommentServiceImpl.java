package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.Comment;
import com.uniobh.yoho.pojo.CommentLike;
import com.uniobh.yoho.pojo.User;
import com.uniobh.yoho.service.CommentLikeService;
import com.uniobh.yoho.service.CommentService;
import com.uniobh.yoho.mapper.CommentMapper;
import com.uniobh.yoho.service.UserService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddCommentVo;
import com.uniobh.yoho.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 23249
* @description 针对表【t_comment】的数据库操作Service实现
* @createDate 2022-10-03 20:35:53
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private UserService userService;

    @Autowired
    private CommentLikeService commentLikeService;

    /**
     * 根据帖子id查询
     * @param IId 帖子id
     * @return
     */
    @Override
    public ResultUtil<List<CommentVo>> getCommentByIId(Integer IId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(IId != 0 && IId != null, Comment::getIId, IId);
        List<Comment> commentList = this.list(queryWrapper);
        ArrayList<CommentVo> res = new ArrayList<>();
        for (int i = 0; i < commentList.size() && commentList != null; i++) {
            Comment comment = commentList.get(i);
            if(comment.getParentId() == 0) {   // 一级评论
                CommentVo commentVo = new CommentVo();
                commentVo.setMId(comment.getId());
                commentVo.setContent(comment.getContent());
                commentVo.setTime(comment.getTime());
                commentVo.setLike(comment.getLikeCount());
                User user = userService.getUserById(comment.getUserId()).getData();
                User u = new User();
                if(user != null) {
                    u.setId(user.getId());
                    u.setAvatar(user.getAvatar());
                    u.setNickname(user.getNickname());
                }
                commentVo.setUser(u);
                List<CommentVo> voList = new ArrayList<>();
                for (int j = 0; j < commentList.size(); j++) {
                    if(j == i || commentList.get(j).getParentId() == 0) continue;   // 一级评论和本条评论跳过
                    CommentVo vo = null;
                    if(commentList.get(j).getParentId() == commentVo.getMId() && commentList.get(j).getToMId() == 0) {  // 二级评论
                        vo = new CommentVo();
                        vo.setMId(commentList.get(j).getId());
                        vo.setContent(commentList.get(j).getContent());
                        vo.setTime(commentList.get(j).getTime());
                        vo.setPId(commentList.get(j).getParentId());
                        User user1 = userService.getUserById(commentList.get(j).getUserId()).getData();
                        User u1 = new User();
                        if(user1 != null) {
                            u1.setId(user1.getId());
                            u1.setAvatar(user1.getAvatar());
                            u1.setNickname(user1.getNickname());
                        }
                        vo.setUser(u1);
                        vo.setLike(commentList.get(j).getLikeCount());
                    }else if(commentList.get(j).getParentId() == commentVo.getMId() && commentList.get(j).getToMId() != 0) { // 二级评论回复
                        vo = new CommentVo();
                        vo.setMId(commentList.get(j).getId());
                        vo.setContent(commentList.get(j).getContent());
                        vo.setTime(commentList.get(j).getTime());
                        vo.setPId(commentList.get(j).getParentId());
                        CommentVo data = getCommentByToMId(commentList.get(j).getToMId()).getData();
                        vo.setToMId(data);
                        User user1 = userService.getUserById(commentList.get(j).getUserId()).getData();
                        User u1 = new User();
                        if(user != null) {
                            u1.setId(user1.getId());
                            u1.setAvatar(user1.getAvatar());
                            u1.setNickname(user1.getNickname());
                        }
                        vo.setUser(u1);
                        vo.setLike(commentList.get(j).getLikeCount());
                    }
                    if(vo != null) voList.add(vo);
                }
                commentVo.setChildren(voList);
                res.add(commentVo);
            }
        }
        if(res.size() == 0 || res == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 根据评论id查询评论总条数
     * @param IId 评论id
     * @return
     */
    @Override
    public ResultUtil<Long> getCommentTotalByIId(Integer IId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(IId != 0 && IId != null, Comment::getIId, IId);
        long count = this.count(queryWrapper);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), count);
    }

    /**
     * 根据回复评论id查询评论
     * @param toMId 回复评论id
     * @return
     */
    @Override
    public ResultUtil<CommentVo> getCommentByToMId(Integer toMId) {
        if(toMId != 0 && toMId != null) {
            Comment comment = this.getById(toMId);
            if(comment != null) {
                User user = userService.getUserById(comment.getUserId()).getData();
                CommentVo commentVo = new CommentVo();
                User u = new User();
                if(user != null) {
                    u.setAvatar(user.getAvatar());
                    u.setNickname(user.getNickname());
                }
                commentVo.setUser(u);
                return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), commentVo);
            }
        }
        return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
    }

    @Override
    public ResultUtil<Boolean> addCommentByAddCommentVo(AddCommentVo addCommentVo) {
        Comment comment = new Comment();
        comment.setIId(addCommentVo.getIId());
        comment.setContent(addCommentVo.getContent());
        if(addCommentVo.getPId() == null) comment.setParentId(0);
        else comment.setParentId(addCommentVo.getPId());
        comment.setUserId(addCommentVo.getUId());
        if(addCommentVo.getToMId() == null) comment.setToMId(0);
        else comment.setToMId(addCommentVo.getToMId());
        boolean b = this.save(comment);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), b);
    }

    /**
     * 添加点赞数
     * @param id
     * @param uId
     * @return
     */
    @Override
    public ResultUtil<Boolean> addLike(Integer id, Integer uId) {
        Boolean data = commentLikeService.isLike(id, uId).getData();
        if(data) return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
        Comment comment = this.getById(id);
        comment.setLikeCount(comment.getLikeCount() + 1);
        boolean b = this.updateById(comment);
        if(!b) return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
        commentLikeService.addCommentLike(id, uId);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), true);
    }
}