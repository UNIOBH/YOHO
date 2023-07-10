package com.uniobh.yoho.vo;

import com.uniobh.yoho.enums.GoodsStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyGoodsVo {
    private Integer id; // 商品id
    private String img; // 商品主图
    private String name;    // 商品名称
    private String details; // 详情
    private Integer state;   // 商品状态
    private BigDecimal price;   // 价格
    private Integer hits;   // 点击量
    private Date time;  // 发布时间
    private Date lastTime;  // 最后修改时间
}
