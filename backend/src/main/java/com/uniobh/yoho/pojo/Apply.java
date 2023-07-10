package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.uniobh.yoho.enums.ApplyStateEnum;
import lombok.Data;

/**
 * 
 * @TableName t_apply
 */
@TableName(value ="t_apply")
@Data
public class Apply implements Serializable {
    /**
     * 用户申请id
     */
    @TableId(type = IdType.AUTO, value = "a_id")
    private Integer id;

    /**
     * 省份id（外键）
     */
    @TableField("a_p_id")
    private Integer pId;

    /**
     * 城市id（外键）
     */
    @TableField("a_c_id")
    private Integer cId;

    /**
     * 学校名称
     */
    @TableField("a_s_name")
    private String sName;

    /**
     * 申请状态（1：同意，0：拒绝，2：未处理）
     */

    @TableField("a_state")
    private ApplyStateEnum state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}