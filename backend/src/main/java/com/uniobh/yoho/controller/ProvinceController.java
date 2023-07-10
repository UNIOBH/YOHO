package com.uniobh.yoho.controller;

import com.uniobh.yoho.pojo.Province;
import com.uniobh.yoho.service.ProvinceService;
import com.uniobh.yoho.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    /**
     * 查询全部省份
     * @return
     */
    @GetMapping("/province")
    public ResultUtil<List<Province>> getAll() {
        return provinceService.getAll();
    }
}
