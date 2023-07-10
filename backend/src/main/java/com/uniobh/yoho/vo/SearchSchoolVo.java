package com.uniobh.yoho.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchSchoolVo {
    private Integer id; // 学校id
    private String pName;   // 省份名称
    private String cName;   // 城市名称
    private String sName;   // 学校名称
}
