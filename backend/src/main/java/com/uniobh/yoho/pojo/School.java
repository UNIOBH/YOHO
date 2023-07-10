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
 * @TableName t_school
 */
@TableName(value ="t_school")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School implements Serializable {
    /**
     * 学校id
     */
    @TableId(type = IdType.AUTO, value = "s_id")
    private Integer id;

    /**
     * 学校名称
     */
    @TableField("s_name")
    private String name;

    /**
     * 城市id（外键）
     */
    @TableField("c_id")
    private Integer cId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}