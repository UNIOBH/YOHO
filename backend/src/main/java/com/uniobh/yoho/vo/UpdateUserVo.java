package com.uniobh.yoho.vo;

import com.uniobh.yoho.enums.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserVo {
    private Integer id;
    private String username;    // 用户名
    private String nickname;    // 昵称
    private Integer sex;    // 性别
}
