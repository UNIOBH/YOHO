package com.uniobh.yoho.controller;

import com.uniobh.yoho.pojo.GoodsCategory;
import com.uniobh.yoho.service.GoodsCategoryService;
import com.uniobh.yoho.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GoodsCategoryController {
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @GetMapping("/goods-category")
    public ResultUtil<List<GoodsCategory>> getAll() {
        return goodsCategoryService.getAll();
    }

    /**
     * 根据类别名称添加类别
     * @param
     * @return
     */
    @PostMapping("/goods-category/{cName}")
    public ResultUtil<Boolean> insertCategoryName(@PathVariable("cName") String cName){
        return goodsCategoryService.insertGoodsCategory(cName);
    }

    /**
     * 根据商品类别id逻辑删除商品类别
     * @param cId
     *
     * @return
     */
    @DeleteMapping("/goods-category/{cId}")
    public ResultUtil<Integer> deleteCategoryByCId(@PathVariable("cId") Integer cId){
        return goodsCategoryService.deleteCategoryByCId(cId);
    }

    /**
     * 根据类别id和类别名称分页查询
     * @param current
     * @param size
     * @param cId
     * @param cName
     * @return
     */
    @GetMapping("/goods-category/{current}/{size}/{cName}/{cId}")
    public ResultUtil<Map<String, Object>> selectCategoeryByPage(@PathVariable("current") Integer current, @PathVariable("size") Integer size, @PathVariable("cId") Integer cId, @PathVariable("cName") String cName) {
        if(" ".equals(cName)){
            cName = null;
        }
        return goodsCategoryService.selectCategoeryByPage(current, size, cId, cName);
    }

    /**
     * 根据id修改名称
     * @param id
     * @param newName
     * @return
     */
    @PutMapping("/goods-category/{id}/{newName}")
    public ResultUtil<Boolean> updateNameById(@PathVariable("id") Integer id, @PathVariable("newName") String newName) {
        return goodsCategoryService.updateNameById(id, newName);
    }
}
