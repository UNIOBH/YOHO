package com.uniobh.yoho.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserVo {
    private String name;    // 用户名
    private String nickname;    // 昵称
    private String sex; // 性别
    private Date registerTime;  // 注册时间
    private String email;   // 邮箱地址
    private String avatar; // 头像
    private String sName;   // 学校名称
}
