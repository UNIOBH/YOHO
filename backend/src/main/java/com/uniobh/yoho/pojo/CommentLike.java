package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_comment_like
 */
@TableName(value ="t_comment_like")
@Data
public class CommentLike implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO, value = "c_l_id")
    private Integer id;

    /**
     * 评论id（外键）
     */
    @TableField("c_id")
    private Integer cId;

    /**
     * 点赞用户id（外键）
     */
    @TableField("u_id")
    private Integer uId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}