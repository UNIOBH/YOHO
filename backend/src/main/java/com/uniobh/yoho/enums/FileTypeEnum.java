package com.uniobh.yoho.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    AVATAR("头像", "/upload/avatar/"),
    GOODS("商品图片", "/upload/goods/"),
    CHAT("聊天图片", "/upload/chat/"),
    INVITATION("帖子图片", "/upload/invitation/"),
    ;
    private String name;
    private String path;    // 路径
}
