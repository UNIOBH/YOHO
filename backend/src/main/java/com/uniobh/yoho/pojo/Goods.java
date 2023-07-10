package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.uniobh.yoho.enums.GoodsStateEnum;
import com.uniobh.yoho.enums.RecommendEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_goods
 */
@TableName(value ="t_goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    /**
     * 商品id
     */
    @TableId(type = IdType.AUTO, value = "g_id")
    private Integer id;

    /**
     * 发布用户id（外键）
     */
    @TableField("u_id")
    private Integer uId;

    /**
     * 商品价格
     */
    @TableField("g_price")
    private BigDecimal price;

    /**
     * 商品名称
     */
    @TableField("g_name")
    private String name;

    /**
     * 商品描述
     */
    @TableField("g_describe")
    private String details;

    /**
     * 商品类别（外键）
     */
    @TableField("g_c_id")
    private Integer cId;

    /**
     * 商品状态
     */
    @TableField("g_state")
    private GoodsStateEnum state;

    /**
     * 商品点击量
     */
    @TableField("g_hits")
    private Integer hits;

    /**
     * 商品发布时间
     */
    @TableField("g_release_time")
    private Date releaseTime;

    /**
     * 商品最后修改时间
     */
    @TableField("g_last_time")
    private Date lastTime;

    /**
     * 是否推荐
     */
    @TableField("g_is_recommend")
    private RecommendEnum isRecommend;

    /**
     * 最终成交价格
     */
    @TableField("g_final_price")
    private BigDecimal finalPrice;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}