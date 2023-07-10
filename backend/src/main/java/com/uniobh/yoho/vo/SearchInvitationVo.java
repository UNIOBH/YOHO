package com.uniobh.yoho.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchInvitationVo {
    private Integer current;    // 当前页数
    private Integer id; // 帖子id
    private Boolean isRecommend;    // 是否推荐
    private String keyword; // 搜索关键字
}
