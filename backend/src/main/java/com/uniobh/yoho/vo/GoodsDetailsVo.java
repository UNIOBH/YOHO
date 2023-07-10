package com.uniobh.yoho.vo;

import com.uniobh.yoho.enums.GoodsStateEnum;
import com.uniobh.yoho.pojo.GoodsCategory;
import com.uniobh.yoho.pojo.GoodsPicture;
import com.uniobh.yoho.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailsVo {   // 商品详情
    private Integer id; // 商品id
    private User user;    // 发布用户（外键）
    private String name;    // 商品名称
    private BigDecimal price;   // 商品价格
    private String details; // 商品描述
    private Integer hits;   // 商品点击量
    private Date releaseTime;   // 商品发布时间
    private Date lastTime;  // 商品最后修改时间
    private Integer state;   // 商品状态
    private BigDecimal finalPrice;  // 最终成交价格
    private GoodsCategory category; // 商品类别
    private Integer isRecommend;    // 是否为推荐
    private List<GoodsPicture> pictures;    // 图片集合
}
