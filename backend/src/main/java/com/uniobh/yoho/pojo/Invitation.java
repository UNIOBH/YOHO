package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.uniobh.yoho.enums.RecommendEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_invitation
 */
@TableName(value ="t_invitation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invitation implements Serializable {
    /**
     * 帖子id
     */
    @TableId(type = IdType.AUTO, value = "i_id")
    private Integer id;

    /**
     * 帖子标题
     */
    @TableField("i_title")
    private String title;

    /**
     * 帖子详情
     */
    @TableField("i_details")
    private String details;

    /**
     * 帖子创建时间
     */
    @TableField("i_create_time")
    private Date createTime;

    /**
     * 帖子阅读量
     */
    @TableField("i_read_count")
    private Integer readCount;

    /**
     * 创建帖子用户（外键）
     */
    @TableField("u_id")
    private Integer uId;

    /**
     * 帖子点赞量
     */
    @TableField("i_like_count")
    private Integer likeCount;

    /**
     * 最后修改时间
     */
    @TableField("i_last_time")
    private Date lastTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField("i_del")
    private Integer del;

    /**
     * 是否推荐
     */
    @TableField("i_is_recommend")
    private RecommendEnum isRecommend;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}