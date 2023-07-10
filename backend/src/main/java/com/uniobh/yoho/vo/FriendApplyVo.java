package com.uniobh.yoho.vo;

import com.uniobh.yoho.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendApplyVo {
    private Integer id; // 好友表id
    private User friend;
    private String verification;    // 验证信息
}
