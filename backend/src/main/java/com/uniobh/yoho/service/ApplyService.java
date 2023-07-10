package com.uniobh.yoho.service;

import com.uniobh.yoho.enums.ApplyStateEnum;
import com.uniobh.yoho.pojo.Apply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;

import java.util.Map;

/**
* @author 23249
* @description 针对表【t_apply】的数据库操作Service
* @createDate 2022-11-03 16:51:11
*/
public interface ApplyService extends IService<Apply> {
    ResultUtil<Apply> addApplyByPIdAndCIdAndCname(Integer pId, Integer cId, String sName);

    ResultUtil<Map<String, Object>> getApplyByPage(Integer current, Integer num);

    ResultUtil<Boolean> updateState(Integer cid, Integer id, String name, ApplyStateEnum state);
}
