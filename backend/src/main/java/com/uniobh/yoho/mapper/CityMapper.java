package com.uniobh.yoho.mapper;

import com.uniobh.yoho.pojo.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23249
* @description 针对表【t_city】的数据库操作Mapper
* @createDate 2022-10-03 20:26:58
* @Entity com.uniobh.yoho.pojo.City
*/
@Mapper
public interface CityMapper extends BaseMapper<City> {

}




