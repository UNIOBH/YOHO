package com.uniobh.yoho.vo;

import com.uniobh.yoho.pojo.GoodsPicture;
import com.uniobh.yoho.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationDetailsVo {
    private Integer id; // id
    private String title;   // 标题
    private String details; // 详情
    private Date createTime;    // 创建时间
    private Integer readCount;  // 阅读量
    private User user;  // 发布帖子的用户
    private Integer likeCount;  // 帖子点赞数量
    private Date lastTime;  // 最后一次修改时间
    private Integer recommend;  // 是否为推荐
    private Long totalComment;  // 总评论数
    private List<GoodsPicture> pictures;    // 图片
}
