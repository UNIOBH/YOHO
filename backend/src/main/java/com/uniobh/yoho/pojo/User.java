package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uniobh.yoho.enums.UserSexEnum;
import com.uniobh.yoho.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO, value = "u_id")
    private Integer id;

    /**
     * 用户名
     */
    @TableField("u_name")
    private String name;

    /**
     * 用户密码
     */
    @TableField("u_password")
    private String password;

    /**
     * 用户昵称
     */
    @TableField("u_nickname")
    private String nickname;

    /**
     * 用户性别
     */
    @TableField("u_sex")
    private UserSexEnum sex;

    /**
     * 用户注册时间
     */
    @TableField("u_register_time")
    private Date registerTime;

    @TableField("u_last_time")
    private Date lastTime;

    /**
     * 用户邮箱
     */
    @TableField("u_email")
    private String email;

    /**
     * 用户头像存储地址
     */
    @TableField("u_avatar")
    private String avatar;

    /**
     * 用户所在学校（外键）
     */
    @TableField("s_id")
    private Integer sId;

    /**
     * 逻辑删除
     */
    @TableField("u_del")
    @TableLogic
    private Integer del;

    /**
     * 用户类型
     */
    @TableField("u_type")
    private UserTypeEnum type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}