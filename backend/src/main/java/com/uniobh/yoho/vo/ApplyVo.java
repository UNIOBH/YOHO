package com.uniobh.yoho.vo;

import com.uniobh.yoho.pojo.City;
import com.uniobh.yoho.pojo.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyVo {
    private Integer id; // 申请id
    private Province province;   // 省份
    private City city;   // 城市
    private String name;    // 学校名称
}
