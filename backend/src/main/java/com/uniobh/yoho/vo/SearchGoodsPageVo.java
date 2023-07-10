package com.uniobh.yoho.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchGoodsPageVo {
    private Integer current;    // 当前页数
    private Boolean isRecommend;    // 是否为推荐
    private Integer cId;    // 类别id
    private String keyword; // 搜索关键字
}
