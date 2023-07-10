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
 * @TableName t_city
 */
@TableName(value ="t_city")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable {
    /**
     * 城市id
     */
    @TableId(type = IdType.AUTO, value = "c_id")
    private Integer id;

    /**
     * 省份id（外键）
     */
    @TableField("p_id")
    private Integer pId;

    /**
     * 城市名称
     */
    @TableField("c_name")
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}