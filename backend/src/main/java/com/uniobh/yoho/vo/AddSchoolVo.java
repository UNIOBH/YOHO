package com.uniobh.yoho.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSchoolVo {
    @JsonProperty("cId")
    private Integer cId;    // 城市id
    @JsonProperty("name")
    private String name;    // 学校名称
}
