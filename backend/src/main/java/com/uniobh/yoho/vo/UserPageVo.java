package com.uniobh.yoho.vo;

import com.uniobh.yoho.enums.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageVo {
    private Integer uId; // 用户id
    private  String uName ; // 用户名
    private String uNickName; // 用户昵称
    private Integer userSex; // 用户性别
    private String email; // 用户邮箱
    private Date registerTime; // 注册时间
    private Date lastTime; // 更新时间
    private String sName; // 学校名称
    private Integer uDel; // 是否已注销（逻辑删除）
}
