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
 * @TableName t_province
 */
@TableName(value ="t_province")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Province implements Serializable {
    /**
     * 省份id
     */
    @TableId(type = IdType.AUTO, value = "p_id")
    private Integer id;

    /**
     * 省份名称
     */
    @TableField("p_name")
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}