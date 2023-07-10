package com.uniobh.yoho.vo;

import com.uniobh.yoho.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Integer mId;    // 评论id
    private String content; // 评论内容
    private Date time;  // 评论时间
    private Integer pId;    // 父评论id，一级评论时为0，二级评论指向mId
    private User user;  // 评论用户
    private Integer like;   // 点赞量
    private CommentVo toMId;  // 回复目标评论的id，回复二级评论，指向mId
    private List<CommentVo> children;   // 子评论
}
