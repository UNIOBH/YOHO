package com.uniobh.yoho.service;

import com.uniobh.yoho.pojo.School;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_school】的数据库操作Service
* @createDate 2022-10-03 21:12:48
*/
public interface SchoolService extends IService<School> {
    ResultUtil<List<School>> getSchoolByCityId(Integer cId);

    ResultUtil<Boolean> addSchoolByCIdAndName(Integer cId, String name);

    ResultUtil<Map<String, Object>> getSchoolByPage(Integer current, Integer num);

    ResultUtil<School> getSchoolById(Integer id);

    ResultUtil<List<School>> getAll();
}
