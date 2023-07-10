package com.uniobh.yoho.service;

import com.uniobh.yoho.pojo.GoodsCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_goods_category】的数据库操作Service
* @createDate 2022-10-03 21:04:03
*/
public interface GoodsCategoryService extends IService<GoodsCategory> {
    ResultUtil<List<GoodsCategory>> getAll();

    ResultUtil<GoodsCategory> getGoodsCategoryById(Integer id);

    /** lbf
     * 根据类别名称添加类别
     * @param cName 类别名称
     * @return 商品类别
     */
    ResultUtil<Boolean> insertGoodsCategory(String cName);

    /**
     * 根据商品id删除商品类别（逻辑删除）
     * @param cId
     * @return
     */
    ResultUtil<Integer> deleteCategoryByCId(Integer cId);

    /**
     * 查询类别id和类别名称
     * @return
     */
    ResultUtil<Map<String, Object>> selectCategoeryByPage(Integer current, Integer size, Integer cId, String cName);

    ResultUtil<Boolean> updateNameById(Integer id, String newName);
}
