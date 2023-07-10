package com.uniobh.yoho.service.impl;

import com.uniobh.yoho.enums.EmailTypeEnum;
import com.uniobh.yoho.service.EmailService;
import com.uniobh.yoho.utils.EmailUtil;
import com.uniobh.yoho.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;  // 邮件工具类

    @Autowired
    private TemplateEngine templateEngine;  // 模板

    @Value("${spring.mail.username}")
    private String sendMailer;  // 邮件发送人

    @Override
    public ResultUtil<Map<String, Object>> sendHtmlEmail(String[] to, EmailTypeEnum emailTypeEnum) {
        return EmailUtil.sendHtmlEmail(mailSender, templateEngine, sendMailer, to, emailTypeEnum);
    }
}
