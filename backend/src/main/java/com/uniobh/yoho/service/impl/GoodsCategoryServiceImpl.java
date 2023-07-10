package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.GoodsCategory;
import com.uniobh.yoho.service.GoodsCategoryService;
import com.uniobh.yoho.mapper.GoodsCategoryMapper;
import com.uniobh.yoho.service.GoodsService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.GoodsCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_goods_category】的数据库操作Service实现
* @createDate 2022-10-03 21:04:03
*/
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory>
    implements GoodsCategoryService{

    /**
     * 获取所有分类
     * @return
     */
    @Override
    public ResultUtil<List<GoodsCategory>> getAll() {
        List<GoodsCategory> list = this.list();
        if(list.size() == 0 || list == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), list);
    }

    /**
     * 根据商品类别id查询
     * @param id 商品类别id
     * @return
     */
    @Override
    public ResultUtil<GoodsCategory> getGoodsCategoryById(Integer id) {
        GoodsCategory goodsCategory = null;
        if(id != 0 && id != null) goodsCategory = this.getById(id);
        if(goodsCategory == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), goodsCategory);
    }

    /** lbf
     * 根据类别名称添加类别
     * @param cName 类别名称
     * @return
     */
    @Override
    public ResultUtil<Boolean> insertGoodsCategory(String cName) {
        GoodsCategory category = new GoodsCategory();
        category.setName(cName);
        boolean res = this.save(category);
        if(res == false){
            return ResultUtil.error(ResultStatusEnum.FAIL.getCode(),  "添加失败");
        }
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 根据商品类别id删除商品类别（逻辑删除）
     * @param cId 商品类别id
     * @return 被删除类别的id
     */
    @Override
    public ResultUtil<Integer> deleteCategoryByCId(Integer cId) {
        LambdaUpdateWrapper<GoodsCategory> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(cId != null && cId != 0, GoodsCategory::getId,  cId);
        boolean res = this.remove(updateWrapper);
        if (res == false) return ResultUtil.success(ResultStatusEnum.FAIL.getCode(), 0);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), cId);
    }

    /** lbf
     *  根据商品id和商品名称分页查询
     * @param current 当前页数
     * @param size 当前页的条数
     * @param cId 类别id
     * @param cName 类别名称
     * @return
     */
    @Override
    public ResultUtil<Map<String, Object>> selectCategoeryByPage(Integer current, Integer size, Integer cId,
                                                                 String cName)  {
        LambdaQueryWrapper<GoodsCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(cId != null && cId != 0, GoodsCategory::getId, cId)
                .like(cName != null && !("".equals(cName)), GoodsCategory::getName, cName);
        Page<GoodsCategory> pageCategory = new Page<>(current, size);
        Page<GoodsCategory> page = this.page(pageCategory, queryWrapper);
        List<GoodsCategory> records = page.getRecords();
        long pages = page.getPages();
        if(pages == 0 || records == null || records.size() == 0){
            return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        }
        HashMap<String, Object> res = new HashMap<>();
        res.put("pages", pages);
        ArrayList<GoodsCategoryVo> list = new ArrayList<>();
        for (GoodsCategory goodsCategory : records){
            GoodsCategoryVo categoryVo = new GoodsCategoryVo();
            categoryVo.setCId(goodsCategory.getId());
            categoryVo.setCName(goodsCategory.getName());
            categoryVo.setCDel(goodsCategory.getDel());
            list.add(categoryVo);
        }
        res.put("list", list);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 根据id修改名称
     * @param id
     * @param newName
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateNameById(Integer id, String newName) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId(id);
        goodsCategory.setName(newName);
        boolean b = this.updateById(goodsCategory);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

}




