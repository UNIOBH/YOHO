package com.uniobh.yoho.controller;

import com.uniobh.yoho.pojo.City;
import com.uniobh.yoho.service.CityService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.ChatRecordsVo;
import com.uniobh.yoho.vo.SendMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/city/{pId}")
    public ResultUtil<List<City>> getCityByPId(@PathVariable("pId") Integer pId) {
        return cityService.getCityByProvinceId(pId);
    }

}
