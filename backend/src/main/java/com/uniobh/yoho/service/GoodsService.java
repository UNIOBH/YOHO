package com.uniobh.yoho.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.uniobh.yoho.enums.GoodsStateEnum;
import com.uniobh.yoho.enums.RecommendEnum;
import com.uniobh.yoho.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.pojo.GoodsCategory;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddGoodsVo;
import com.uniobh.yoho.vo.GoodsDetailsVo;
import com.uniobh.yoho.vo.UpdateGoodsVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_goods】的数据库操作Service
* @createDate 2022-10-03 20:54:14
*/
public interface GoodsService extends IService<Goods> {

    ResultUtil<Map<String, Object>> getGoodsByPage(Integer current, Integer num, RecommendEnum recommendEnum, Integer cId,
                                                   Integer sId, Integer uId, GoodsStateEnum gState, String keyword);

    ResultUtil<GoodsDetailsVo> getGoodsById(Integer id);

    ResultUtil<Boolean> addGoodsHitsById(Integer id);

    ResultUtil<Boolean> updateRecommendByIds(List<Integer> ids, RecommendEnum recommendEnum);

    ResultUtil<Boolean> updateStateByIds(List<Integer> ids, GoodsStateEnum goodsStateEnum);

    ResultUtil<GoodsCategory> getCategoryById(Integer id);

    ResultUtil<Goods> addGoods(AddGoodsVo addGoodsVo);

    ResultUtil<Boolean> updateFinalPrice(Integer id, BigDecimal price);

    ResultUtil<Map<String, Object>> getGoodsByUId(Integer uId, GoodsStateEnum state);

    ResultUtil<Boolean> updateCategoryByIds(List<Integer> ids, Integer cId);

    ResultUtil<List<Integer>> getIdsByCId(Integer cId);

    ResultUtil<Boolean> updateGoodsById(UpdateGoodsVo updateGoodsVo);
}
