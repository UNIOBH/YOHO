package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.uniobh.yoho.enums.FriendStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_friend
 */
@TableName(value ="t_friend")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend implements Serializable {
    /**
     * 好友id
     */
    @TableId(type = IdType.AUTO, value = "f_id")
    private Integer id;

    /**
     * 用户id（外键）
     */
    @TableField("u_id")
    private Integer uId;

    /**
     * 用户好友的id（外键）
     */
    @TableField("f_u_id")
    private Integer friendUserId;

    /**
     * 好友备注
     */
    @TableField("f_remark")
    private String remark;

    /**
     * 好友状态
     */
    @TableField("f_state")
    private FriendStateEnum state;

    /**
     * 好友验证信息
     */
    @TableField("f_verification_information")
    private String VerificationInformation;

    @TableField("f_del")
    @TableLogic
    private Integer del;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}