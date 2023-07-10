package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_goods_category
 */
@TableName(value ="t_goods_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategory implements Serializable {
    /**
     * 商品类别id
     */
    @TableId(type = IdType.AUTO, value = "g_c_id")
    private Integer id;

    /**
     * 商品类别名称
     */
    @TableField("g_c_name")
    private String name;

    /**
     * 逻辑删除
     */
    @TableField("g_c_del")
    @TableLogic
    private Integer del;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}