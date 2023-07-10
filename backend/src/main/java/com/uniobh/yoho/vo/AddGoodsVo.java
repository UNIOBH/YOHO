package com.uniobh.yoho.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddGoodsVo {
    @JsonProperty("uId")
    private Integer uId;    // 用户id
    @JsonProperty("price")
    private BigDecimal price;   // 价格
    @JsonProperty("name")
    private String name;    // 名称
    @JsonProperty("describe")
    private String describe;    // 描述
    @JsonProperty("cId")
    private Integer cId;    // 所在类别id
}
