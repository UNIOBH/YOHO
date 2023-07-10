package com.uniobh.yoho.utils;

import com.uniobh.yoho.enums.EmailTypeEnum;
import com.uniobh.yoho.enums.ResultStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class EmailUtil {

    /**
     * 替换模板
     * @param code 验证码
     * @param isRegister 是否为注册验证码
     * @return 替换后的模板
     */
//    public static String createContext(Integer code, boolean isRegister) {
//        ClassPathResource resource = null;
//        // 加载模板
//        if(isRegister) resource = new ClassPathResource("static/registercode.ftl");
//        else resource = new ClassPathResource("static/passwordcode.ftl");
//        InputStream inputStream = null;
//        BufferedReader fileReader = null;
//        StringBuffer buffer = new StringBuffer();
//        String line = "";
//        try {
//            inputStream = resource.getInputStream();
//            fileReader = new BufferedReader(new InputStreamReader(inputStream));
//            while((line = fileReader.readLine()) != null) buffer.append(line);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(fileReader != null) {
//                try {
//                    fileReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return MessageFormat.format(buffer.toString(), code);
//    }

    /**
     * 发送html邮件
     * @param javaMailSender 发送邮件工具类
     * @param sender 发送人
     * @param to 收件人
     * @param emailTypeEnum 邮件类型
     * @return
     */
    public static ResultUtil<Map<String, Object>> sendHtmlEmail(JavaMailSender javaMailSender,
                                                                TemplateEngine templateEngine,
                                                                String sender,
                                                                String[] to, EmailTypeEnum emailTypeEnum) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true); // 第二个参数表示为发送的邮件格式是否为富文本
            if(sender == null) return ResultUtil.error("邮件发送人为null");
            if(to.length == 0 || to == null) return ResultUtil.error("邮件收件人为null");
            mimeMessageHelper.setFrom(sender);  // 设置邮件发送人
            mimeMessageHelper.setTo(to); // 设置邮件收件人
            // 设置邮箱主题
            mimeMessageHelper.setSubject(emailTypeEnum.getSubject());
            Integer code = (int) ((Math.random() * 9 + 1) * 10000);
            Context context = new Context();
            context.setVariable("code", code);
            String  process= templateEngine.process(emailTypeEnum.getAddress(), context);
            mimeMessageHelper.setText(process, true);   // 设置邮件内容，第二个参数表示是否为html文件
            javaMailSender.send(mimeMessage);   // 发送邮件
            HashMap<String, Object> map = new HashMap<>();
            map.put("sender", sender);
            map.put("code", code);
            map.put("to", to);
            return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), "发送成功！", map);
        } catch (MessagingException e) {
            return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "邮件发送失败");
        }
    }
}
