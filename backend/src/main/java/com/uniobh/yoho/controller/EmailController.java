package com.uniobh.yoho.controller;

import com.uniobh.yoho.enums.EmailTypeEnum;
import com.uniobh.yoho.service.EmailService;
import com.uniobh.yoho.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class EmailController {
    @Autowired
    private EmailService emailService;

    /**
     * 注册发送邮件验证码
     * @param to 收件人
     * @return
     */
    @PostMapping("/register-code")
    public ResultUtil<Map<String, Object>> register(@RequestBody String[] to) {
        return emailService.sendHtmlEmail(to, EmailTypeEnum.REGISTER);
    }

    @PostMapping("/forget-password")
    public ResultUtil<Map<String, Object>> forgetPassword(@RequestBody String[] to) {
        return emailService.sendHtmlEmail(to, EmailTypeEnum.FORGETPASSWORD);
    }

    @PostMapping("/alter-password")
    public ResultUtil<Map<String, Object>> alterPassword(@RequestBody String[] to) {
        return emailService.sendHtmlEmail(to, EmailTypeEnum.ALTERPASSWORD);
    }
}
