package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_comment
 */
@TableName(value ="t_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO, value = "m_id")
    private Integer id;

    /**
     * 帖子id（外键）
     */
    @TableField("i_id")
    private Integer iId;

    /**
     * 评论内容
     */
    @TableField("m_content")
    private String content;

    /**
     * 评论时间
     */
    @TableField("m_time")
    private Date time;

    /**
     * 父评论id（用户二级评论，连接本表id）
     */
    @TableField("m_p_id")
    private Integer parentId;

    /**
     * 评论用户id（外键）
     */
    @TableField("u_id")
    private Integer userId;

    /**
     * 点赞量
     */
    @TableField("m_like_count")
    private Integer likeCount;

    /**
     * 回复目标评论的id（回复二级评论，连接本表id）
     */
    @TableField("m_to_m_id")
    private Integer toMId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}