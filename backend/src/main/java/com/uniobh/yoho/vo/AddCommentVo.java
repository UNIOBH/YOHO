package com.uniobh.yoho.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentVo {
    @JsonProperty("iId")
    private Integer iId;    // 帖子id
    @JsonProperty("content")
    private String content; // 评论内容
    @JsonProperty("pId")
    private Integer pId;    // 父评论id
    @JsonProperty("uId")
    private Integer uId;    // 用户id
    @JsonProperty("toMId")
    private Integer toMId;  // 回复目标评论id
}
