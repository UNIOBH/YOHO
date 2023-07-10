package com.uniobh.yoho.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellGoodsVo {
    private Integer id; // 商品价格
    private BigDecimal price;   // 成交价格
}
