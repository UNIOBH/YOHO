package com.uniobh.yoho.vo;

import com.uniobh.yoho.enums.ChatTypeEnum;
import com.uniobh.yoho.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewestChatVo {
    private Integer id;
    private User to;
    private String content;
    private Date time;
    private String remake;
    private ChatTypeEnum type;
}
