package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_invitation_like
 */
@TableName(value ="t_invitation_like")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitationLike implements Serializable {
    /**
     * 帖子点赞id
     */
    @TableId(type = IdType.AUTO, value = "i_l_id")
    private Integer id;

    /**
     * 帖子id
     */
    @TableField("i_id")
    private Integer iId;

    /**
     * 点赞用户id
     */
    @TableField("u_id")
    private Integer uId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}