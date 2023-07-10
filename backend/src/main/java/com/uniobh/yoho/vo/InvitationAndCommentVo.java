package com.uniobh.yoho.vo;

import com.uniobh.yoho.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitationAndCommentVo {
    private Integer iId;    // 帖子id
    private String title;   // 帖子标题
    private String details; // 帖子详情
    private Date createTime; // 帖子创建时间
    private Integer iRead;  // 帖子阅读量
    private User user;  // 创建帖子用户
    private Integer like;   // 帖子点赞量
    private Date lastTime;  // 帖子最后修改时间
    private List<CommentVo> comments;   // 评论
    private Long cTotal; // 评论总条数
}
