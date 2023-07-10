package com.uniobh.yoho.vo;

import com.uniobh.yoho.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFriendVo {
    private Integer id; // id
    private User friend;    // 好友
    private String remake;  // 备注
}
