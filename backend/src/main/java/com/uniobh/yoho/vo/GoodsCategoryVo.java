package com.uniobh.yoho.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategoryVo {
    private Integer cId; // 类别id
    private String cName; // 类别名称
    private Integer cDel; // 逻辑删除
}