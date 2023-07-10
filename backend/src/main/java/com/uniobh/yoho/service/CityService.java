package com.uniobh.yoho.service;

import com.uniobh.yoho.pojo.City;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_city】的数据库操作Service
* @createDate 2022-10-03 20:26:58
*/
public interface CityService extends IService<City> {
    ResultUtil<List<City>> getCityByProvinceId(Integer pId);

    ResultUtil<City> getNameById(Integer id);

    ResultUtil<Map<String, Object>> getCityAndProvinceById(Integer id);
}
