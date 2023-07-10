package com.uniobh.yoho.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadGoodsPictureVo {
    @JsonProperty("img")
    MultipartFile img;
    @JsonProperty("uId")
    private Integer uId; // 用户id
    @JsonProperty("gId")
    private Integer gId; // 商品id
}
