package com.uniobh.yoho.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.uniobh.yoho.enums.GoodsMainPictureEnum;
import com.uniobh.yoho.enums.PictureStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_goods_picture
 */
@TableName(value ="t_goods_picture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsPicture implements Serializable {
    /**
     * 商品图片id
     */
    @TableId(type = IdType.AUTO, value = "g_p_id")
    private Integer id;

    /**
     * 商品id（外键）
     */
    @TableField("g_Id")
    private Integer gId;

    /**
     * 商品图片url
     */
    @TableField("g_p_img")
    private String img;

    @TableField("g_p_is_main")
    private GoodsMainPictureEnum isMain;

    @TableField("g_p_state")
    private PictureStateEnum state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}