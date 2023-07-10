package com.uniobh.yoho.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGoodsVo {
    private Integer id;
    private String name;
    private String details;
    private Integer cId;
    private BigDecimal price;
}
