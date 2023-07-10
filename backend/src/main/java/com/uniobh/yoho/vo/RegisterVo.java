package com.uniobh.yoho.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVo {
    private String username;
    private String nickname;
    private String sex;
    private String password;
    private String email;
    private Integer school;
}
