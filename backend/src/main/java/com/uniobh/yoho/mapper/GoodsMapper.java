package com.uniobh.yoho.mapper;

import com.uniobh.yoho.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23249
* @description 针对表【t_goods】的数据库操作Mapper
* @createDate 2022-10-03 20:54:14
* @Entity com.uniobh.yoho.pojo.Goods
*/
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}




