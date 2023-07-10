package com.uniobh.yoho;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.uniobh.yoho.mapper")
public class YoHo {
    public static void main(String[] args) {
        SpringApplication.run(YoHo.class, args);
    }
}
