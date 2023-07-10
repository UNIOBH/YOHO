package com.uniobh.yoho.service;

import com.uniobh.yoho.pojo.Province;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;

import java.util.List;

/**
* @author 23249
* @description 针对表【t_province】的数据库操作Service
* @createDate 2022-10-03 21:11:45
*/
public interface ProvinceService extends IService<Province> {
    ResultUtil<List<Province>> getAll();

    ResultUtil<Province> getProvinceById(Integer id);

}
