package com.uniobh.yoho.controller;

import com.uniobh.yoho.enums.GoodsStateEnum;
import com.uniobh.yoho.enums.RecommendEnum;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.Goods;
import com.uniobh.yoho.pojo.GoodsCategory;
import com.uniobh.yoho.service.GoodsService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddGoodsVo;
import com.uniobh.yoho.vo.GoodsDetailsVo;
import com.uniobh.yoho.vo.SellGoodsVo;
import com.uniobh.yoho.vo.UpdateGoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询推荐商品
     * @param current 当前页
     * @return
     */
    @GetMapping("/goods/hot/{current}/{num}/{sId}")
    public ResultUtil<Map<String, Object>> getGoodsByRecommend(@PathVariable("current") Integer current,
                                               @PathVariable("num") Integer num,
                                               @PathVariable("sId") Integer sId) {
        return goodsService.getGoodsByPage(current, num, RecommendEnum.YES,null, sId, null, GoodsStateEnum.RELEASE, null);
    }

    /**
     *
     * @param current
     * @param num
     * @param sId
     * @return
     */
    @GetMapping("/goods/{current}/{num}/{sId}")
    public ResultUtil<Map<String, Object>> getGoodsByPage(@PathVariable("current") Integer current,
                                                          @PathVariable("num") Integer num,
                                                          @PathVariable("sId") Integer sId) {
        return goodsService.getGoodsByPage(current, num,null,null, sId, null, GoodsStateEnum.RELEASE, null);
    }

    /**
     * 根据关键字分页查询
      * @param current
     * @param num
     * @param keyword
     * @param sId
     * @return
     */
    @GetMapping("/goods/{current}/{num}/{sId}/{cId}/{keyword}")
    public ResultUtil<Map<String, Object>> searchGoods(@PathVariable("current") Integer current,
                                                             @PathVariable("num") Integer num,
                                                             @PathVariable("keyword") String keyword,
                                                             @PathVariable("sId") Integer sId,
                                                             @PathVariable("cId") Integer cId) {
        return goodsService.getGoodsByPage(current, num, null, cId, sId, null, GoodsStateEnum.RELEASE, keyword);
    }

    /**
     * 根据类别id分页查询
     * @param current
     * @param num
     * @param sId
     * @param cId
     * @return
     */
    @GetMapping("/goods/{current}/{num}/{sId}/{cId}")
    public ResultUtil<Map<String, Object>> getGoodsByCId(@PathVariable("current") Integer current,
                                                         @PathVariable("num") Integer num,
                                                         @PathVariable("sId") Integer sId,
                                                         @PathVariable("cId") Integer cId) {
        return goodsService.getGoodsByPage(current, num, null, cId, sId, null, GoodsStateEnum.RELEASE, null);
    }

    /**
     * 分页查询
     * @param current
     * @param num
     * @param gState
     * @param isRecommend
     * @param cId
     * @param uId
     * @return
     */
    @GetMapping("/goods/{current}/{num}/{isRecommend}/{cId}/{gState}/{uId}")
    public ResultUtil<Map<String, Object>> getGoods(@PathVariable("current") Integer current,
                                                    @PathVariable("num") Integer num,
                                                    @PathVariable("gState") Integer gState,
                                                    @PathVariable("isRecommend") Integer isRecommend,
                                                    @PathVariable("cId") Integer cId,
                                                    @PathVariable("uId") Integer uId) {
        RecommendEnum recommendEnum = null;
        if(isRecommend == 1) recommendEnum = RecommendEnum.YES;
        else if(isRecommend == 0) recommendEnum = RecommendEnum.NO;
        GoodsStateEnum goodsStateEnum = null;
        if(gState == GoodsStateEnum.RELEASE.getState())  goodsStateEnum = GoodsStateEnum.RELEASE;
        else if(gState == GoodsStateEnum.SOLD.getState()) goodsStateEnum = GoodsStateEnum.SOLD;
        else if(gState == GoodsStateEnum.BAN.getState()) goodsStateEnum = GoodsStateEnum.BAN;
        return goodsService.getGoodsByPage(current, num, recommendEnum, cId, 0, uId, goodsStateEnum, null);
    }

    /**
     * 根据商品id查询
     * @param id 商品id
     * @return
     */
    @GetMapping("/goods/{id}")
    public ResultUtil<GoodsDetailsVo> getGoodsById(@PathVariable("id") Integer id) {
        return goodsService.getGoodsById(id);
    }

    @PutMapping("/goods/hits/{id}")
    public ResultUtil<Boolean> addGoodsHits(@PathVariable("id") Integer id) {
        return goodsService.addGoodsHitsById(id);
    }

    @PutMapping("/goods/hot/{recommend}")
    public ResultUtil<Boolean> updateRecommendByIds(@PathVariable("recommend") Integer recommend,
                                                   @RequestBody List<Integer> ids) {
        return goodsService.updateRecommendByIds(ids, recommend == 1 ? RecommendEnum.YES : RecommendEnum.NO);
    }

    @PutMapping("/goods/state/{state}")
    public ResultUtil<Boolean> updateStateByIds(@PathVariable("state") Integer state, @RequestBody List<Integer> ids) {
        GoodsStateEnum goodsStateEnum = null;
        if(state == 1) goodsStateEnum = GoodsStateEnum.RELEASE;
        else if(state == 2) goodsStateEnum = GoodsStateEnum.SOLD;
        else if(state == 3) goodsStateEnum = GoodsStateEnum.BAN;
        return goodsService.updateStateByIds(ids, goodsStateEnum);
    }

    @GetMapping("/goods/category/{id}")
    public ResultUtil<GoodsCategory> getCategoryById(@PathVariable("id") Integer id) {
        return goodsService.getCategoryById(id);
    }

    @PostMapping("/goods")
    public ResultUtil<Goods> addGoods(@RequestBody AddGoodsVo addGoodsVo) {
        return goodsService.addGoods(addGoodsVo);
    }

    @PutMapping("/goods")
    public ResultUtil<Boolean> updateFinalPrice(@RequestBody SellGoodsVo sellGoodsVo) {
        return goodsService.updateFinalPrice(sellGoodsVo.getId(), sellGoodsVo.getPrice());
    }

    @GetMapping("/goods/{uId}/{state}")
    public ResultUtil<Map<String, Object>> getGoodsByUId(@PathVariable("uId") Integer uId,
                                                         @PathVariable("state") Integer state) {
        GoodsStateEnum goodsStateEnum = null;
        if(state == GoodsStateEnum.RELEASE.getState()) goodsStateEnum = GoodsStateEnum.RELEASE;
        else if(state == GoodsStateEnum.SOLD.getState()) goodsStateEnum = GoodsStateEnum.SOLD;
        else if(state == GoodsStateEnum.BAN.getState()) goodsStateEnum = GoodsStateEnum.BAN;
        return goodsService.getGoodsByUId(uId, goodsStateEnum);
    }

    @PutMapping("/goods/{cId}/{afterCId}")
    public ResultUtil<Boolean> updateCategoryByIds(@PathVariable("cId") Integer cId, @PathVariable("afterCId") Integer afterCId) {
        List<Integer> list = goodsService.getIdsByCId(cId).getData();
        if(list.size() == 0 || list == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode());
        Boolean data = goodsService.updateCategoryByIds(list, afterCId).getData();
        if(data) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), data);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    @PutMapping("/goods/content")
    public ResultUtil<Boolean> updateGoodsById(@RequestBody UpdateGoodsVo updateGoodsVo) {
        return goodsService.updateGoodsById(updateGoodsVo);
    }

}
