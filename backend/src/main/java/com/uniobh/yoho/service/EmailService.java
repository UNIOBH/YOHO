package com.uniobh.yoho.service;

import com.uniobh.yoho.enums.EmailTypeEnum;
import com.uniobh.yoho.utils.ResultUtil;

import java.util.Map;

public interface EmailService {
    ResultUtil<Map<String, Object>> sendHtmlEmail(String[] to, EmailTypeEnum emailTypeEnum);
}
