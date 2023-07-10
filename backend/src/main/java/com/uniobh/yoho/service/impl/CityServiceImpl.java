package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.City;
import com.uniobh.yoho.pojo.Province;
import com.uniobh.yoho.service.CityService;
import com.uniobh.yoho.mapper.CityMapper;
import com.uniobh.yoho.service.ProvinceService;
import com.uniobh.yoho.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_city】的数据库操作Service实现
* @createDate 2022-10-03 20:26:58
*/
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City>
    implements CityService{

    @Autowired
    private ProvinceService provinceService;

    /**
     * 根据省份id获取城市
     * @param pId 省份id
     * @return
     */
    @Override
    public ResultUtil<List<City>> getCityByProvinceId(Integer pId) {
        LambdaQueryWrapper<City> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(City::getPId, pId);
        List<City> list = this.list(wrapper);
        if(list == null || list.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), list);
    }

    @Override
    public ResultUtil<City> getNameById(Integer id) {
        City city = this.getById(id);
        if(city != null) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), "成功！！", city);
        return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
    }

    /**
     * 根据城市id获取省份和城市
     * @param id 城市id
     * @return
     */
    @Override
    public ResultUtil<Map<String, Object>> getCityAndProvinceById(Integer id) {
        City city = this.getById(id);
        if(city == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        ResultUtil<Province> province = provinceService.getProvinceById(city.getPId());
        if(province == null) ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        HashMap<String, Object> map = new HashMap<>();
        map.put("city", city);
        map.put("province", province.getData());
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), map);
    }
}




