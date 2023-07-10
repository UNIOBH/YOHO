package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.Province;
import com.uniobh.yoho.service.ProvinceService;
import com.uniobh.yoho.mapper.ProvinceMapper;
import com.uniobh.yoho.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 23249
* @description 针对表【t_province】的数据库操作Service实现
* @createDate 2022-10-03 21:11:45
*/
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
    implements ProvinceService{

    /**
     * 获取所有省份
     * @return
     */
    @Override
    public ResultUtil<List<Province>> getAll() {
        List<Province> list = this.list();
        if(list == null || list.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(),"暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), list);
    }

    /**
     * 根据id查询名称
     * @param id
     * @return
     */
    @Override
    public ResultUtil<Province> getProvinceById(Integer id) {
        Province province = this.getById(id);
        if(province != null) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), "成功！", province);
        return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
    }

}




