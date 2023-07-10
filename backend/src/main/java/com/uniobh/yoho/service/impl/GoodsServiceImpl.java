package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.FileTypeEnum;
import com.uniobh.yoho.enums.GoodsStateEnum;
import com.uniobh.yoho.enums.RecommendEnum;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.Goods;
import com.uniobh.yoho.pojo.GoodsCategory;
import com.uniobh.yoho.pojo.GoodsPicture;
import com.uniobh.yoho.pojo.User;
import com.uniobh.yoho.service.GoodsCategoryService;
import com.uniobh.yoho.service.GoodsPictureService;
import com.uniobh.yoho.service.GoodsService;
import com.uniobh.yoho.mapper.GoodsMapper;
import com.uniobh.yoho.service.UserService;
import com.uniobh.yoho.utils.ImageUtil;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddGoodsVo;
import com.uniobh.yoho.vo.GoodsDetailsVo;
import com.uniobh.yoho.vo.MyGoodsVo;
import com.uniobh.yoho.vo.UpdateGoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_goods】的数据库操作Service实现
* @createDate 2022-10-03 20:54:14
*/
@Service
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

    @Autowired
    private GoodsPictureService goodsPictureService;

    @Autowired
    private UserService userService;

    @Autowired
    public GoodsCategoryService goodsCategoryService;

    /**
     * 分页查询
     * @param current 当前页数
     * @param num 一页多少条数据
     * @param recommendEnum 是否为推荐
     * @param cId 类别id
     * @param sId 学校id
     * @param uId 用户id
     * @param gState 商品状态
     * @param keyword 关键字
     * @return
     */
    @Override
    public ResultUtil<Map<String, Object>> getGoodsByPage(Integer current, Integer num, RecommendEnum recommendEnum, Integer cId,
                                                          Integer sId, Integer uId, GoodsStateEnum gState, String keyword) {
        List<Integer> ids = null;
        if(sId != 0 && sId != null) ids = userService.getUIdsBySId(sId).getData();
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(recommendEnum != null, Goods::getIsRecommend, recommendEnum)
                .eq(gState != null, Goods::getState, gState)
                .eq(cId != null && cId != 0, Goods::getCId, cId)
                .eq(uId != null && uId != 0, Goods::getUId, uId)
                .and(keyword != null && !("".equals(keyword)),
                        i -> i.like(Goods::getName, keyword)
                                .or()
                                .like(Goods::getDetails, keyword))
                .in(ids != null && ids.size() != 0, Goods::getUId, ids);
        Page<Goods> goodsPage = new Page<>(current, num);
        IPage<Goods> page = this.page(goodsPage, queryWrapper);
        List<Goods> list = page.getRecords();  // 获取page里的记录
        long total = page.getPages();   // 获取总页数
        if(list == null || list.size() == 0 || total == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        HashMap<String, Object> res = new HashMap<>();
        res.put("total", total);
        ArrayList<GoodsDetailsVo> r = new ArrayList<>();
        for (Goods goods : list) {
            GoodsDetailsVo goodsDetailsVo = new GoodsDetailsVo();
            goodsDetailsVo.setId(goods.getId());
            goodsDetailsVo.setName(goods.getName());
            goodsDetailsVo.setPrice(goods.getPrice());
            goodsDetailsVo.setHits(goods.getHits());
            goodsDetailsVo.setDetails(goods.getDetails());
            goodsDetailsVo.setReleaseTime(goods.getReleaseTime());
            goodsDetailsVo.setLastTime(goods.getLastTime());
            goodsDetailsVo.setState(goods.getState().getState());
            goodsDetailsVo.setFinalPrice(goods.getFinalPrice());
            goodsDetailsVo.setIsRecommend(goods.getIsRecommend().getRecommendCode());
            ResultUtil<GoodsPicture> goodsPictureByMain = goodsPictureService.getGoodsPictureByMain(goods.getId()); // 根据商品id查询主图
            ArrayList<GoodsPicture> pictures = new ArrayList<>();
            // 查询主图成功
            if(goodsPictureByMain.getStatus() == ResultStatusEnum.PERFECT.getCode()) pictures.add(goodsPictureByMain.getData());
            else if(goodsPictureByMain.getStatus() == ResultStatusEnum.NO_DATA.getCode()) { //没有主图数据
                GoodsPicture goodsPicture = new GoodsPicture();
                goodsPicture.setImg(ImageUtil.SERVER_PATH + "/upload/goods/暂无图片.png");
                pictures.add(goodsPicture);
            }
            goodsDetailsVo.setPictures(pictures);
            ResultUtil<User> user = userService.getUserById(goods.getUId());  // 根据用户id查询用户
            User u = new User();
            if(user.getData() != null) {
                u.setId(user.getData().getId());
                u.setNickname(user.getData().getNickname());
            }
            goodsDetailsVo.setUser(u);
            GoodsCategory goodsCategory = goodsCategoryService.getGoodsCategoryById(goods.getCId()).getData();
            goodsDetailsVo.setCategory(goodsCategory);
            r.add(goodsDetailsVo);
        }
        res.put("list", r);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 根据商品id查询
     * @param id 商品id
     * @return
     */
    @Override
    public ResultUtil<GoodsDetailsVo> getGoodsById(Integer id) {
        Goods goods = this.getById(id);
        if(goods == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        ResultUtil<List<GoodsPicture>> pictures = goodsPictureService.getGoodsPictureByGId(id);
        GoodsDetailsVo goodsDetailsVo = new GoodsDetailsVo();
        goodsDetailsVo.setId(goods.getId());
        goodsDetailsVo.setName(goods.getName());
        goodsDetailsVo.setPrice(goods.getPrice());
        goodsDetailsVo.setHits(goods.getHits());
        goodsDetailsVo.setDetails(goods.getDetails());
        goodsDetailsVo.setReleaseTime(goods.getReleaseTime());
        goodsDetailsVo.setLastTime(goods.getLastTime());
        goodsDetailsVo.setCategory(goodsCategoryService.getGoodsCategoryById(goods.getCId()).getData());
        User user = userService.getUserById(goods.getUId()).getData();
        User u = new User();
        if(user != null) {
            u.setId(user.getId());
            u.setAvatar(user.getAvatar());
            u.setNickname(user.getNickname());
        }
        goodsDetailsVo.setUser(u);
        if(pictures.getStatus() == ResultStatusEnum.PERFECT.getCode()) goodsDetailsVo.setPictures(pictures.getData());
        else if(pictures.getStatus() == ResultStatusEnum.NO_DATA.getCode()) goodsDetailsVo.setPictures(null);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), goodsDetailsVo);
    }

    /**
     * 添加商品点击量
     * @param id 商品id
     * @return
     */
    @Override
    public ResultUtil<Boolean> addGoodsHitsById(Integer id) {
        Goods goods = this.getById(id);
        if(goods == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        goods.setHits(goods.getHits() + 1);
        boolean b = this.updateById(goods);
        if(b == false) return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode());
    }

    /** lbf
     * 根据商品id查询商品类别
     * @param id 商品id
     * @return 商品类别
     */
    @Override
    public ResultUtil<GoodsCategory> getCategoryById(Integer id) {
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(id != null && id != 0, Goods::getId, id);
        Goods good = this.getOne(queryWrapper);
        if(good == null){
            return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(),"暂无数据");
        }
        GoodsCategory goodsCategory = goodsCategoryService.getGoodsCategoryById(good.getCId()).getData();
        if(goodsCategory == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), goodsCategory);
    }

    /**
     * 根据商品id修改商品是否为推荐
     * @param ids 商品id构成的集合
     * @param recommendEnum 是否为推荐
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateRecommendByIds(List<Integer> ids, RecommendEnum recommendEnum) {
        boolean b = false;
        ArrayList<Goods> list = new ArrayList<>();
        if(ids != null && ids.size() != 0) {
            for (Integer id : ids) {
                Goods goods = new Goods();
                goods.setId(id);
                goods.setIsRecommend(recommendEnum);
                list.add(goods);
            }
            if(list != null && list.size() != 0) b = this.updateBatchById(list);
        }
        if(b == false) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "修改失败");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
    }

    /**
     * 修改商品状态
     * @param ids 需要需要商品的id构成的集合
     * @param goodsStateEnum 需要修改成的状态
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateStateByIds(List<Integer> ids, GoodsStateEnum goodsStateEnum) {
        boolean b = false;
        if(ids != null && ids != null) {
            ArrayList<Goods> list = new ArrayList<>();
            for (Integer id : ids) {
                Goods goods = new Goods();
                goods.setId(id);
                goods.setState(goodsStateEnum);
                list.add(goods);
            }
            if(list.size() != 0 && list != null) b = this.updateBatchById(list);
            if(b) return  ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        }
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "修改失败");
    }

    /**
     * 添加商品
     * @param addGoodsVo
     * @return
     */
    @Override
    public ResultUtil<Goods> addGoods(AddGoodsVo addGoodsVo) {
        Goods goods = new Goods();
        goods.setUId(addGoodsVo.getUId());
        goods.setName(addGoodsVo.getName());
        goods.setDetails(addGoodsVo.getDescribe());
        goods.setPrice(addGoodsVo.getPrice());
        goods.setCId(addGoodsVo.getCId());
        boolean b = this.save(goods);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), goods);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 根据商品id修改商品成交价格及商品状态
     * @param id 商品id
     * @param price 成交价格
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateFinalPrice(Integer id, BigDecimal price) {
        Goods goods = this.getById(id);
        if(goods == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        goods.setFinalPrice(price);
        goods.setState(GoodsStateEnum.SOLD);
        boolean b = this.updateById(goods);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 根据用户id和商品状态获取商品
     * @param uId
     * @param state
     * @return
     */
    @Override
    public ResultUtil<Map<String, Object>> getGoodsByUId(Integer uId, GoodsStateEnum state) {
        log.info("+++++++++++++++++++++++++++++++++++" + state + "+++++++++++++++++++++++++++++++++++");
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(uId != 0 && uId != null, Goods::getUId, uId).eq(state != null, Goods::getState, state);
        List<Goods> list = this.list(wrapper);
        if(list.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", list.size());  // 商品总数
        Integer count = 0;
        ArrayList<MyGoodsVo> vos = new ArrayList<>();
        for (Goods goods : list) {
            MyGoodsVo vo = new MyGoodsVo();
            vo.setId(goods.getId());
            vo.setName(goods.getName());
            vo.setDetails(goods.getDetails());
            vo.setState(goods.getState().getState());
            vo.setPrice(goods.getPrice());
            vo.setHits(goods.getHits());
            vo.setLastTime(goods.getLastTime());
            vo.setTime(goods.getReleaseTime());
            GoodsPicture picture = goodsPictureService.getGoodsPictureByMain(vo.getId()).getData();
            if(picture != null) vo.setImg(picture.getImg());
            else vo.setImg(ImageUtil.SERVER_PATH + FileTypeEnum.GOODS + "暂无图片.png");
            if(goods.getState() == GoodsStateEnum.SOLD) count++;
            vos.add(vo);
        }
        map.put("sold", count); // 已售出的商品件数
        map.put("list", vos);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), map);
    }

    /**
     * 批量修改商品所在类别
     * @param ids 商品id构成的集合
     * @param cId 类别id
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateCategoryByIds(List<Integer> ids, Integer cId) {
        ArrayList<Goods> list = new ArrayList<>();
        for (Integer id : ids) {
            Goods goods = new Goods();
            goods.setId(id);
            goods.setCId(cId);
            list.add(goods);
        }
        boolean b = this.updateBatchById(list);
        if(!b) return ResultUtil.error(ResultStatusEnum.FAIL.getCode());

        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
    }

    /**
     * 根据类别id获取商品id
     * @param cId 类别id
     * @return
     */
    @Override
    public ResultUtil<List<Integer>> getIdsByCId(Integer cId) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(cId != 0 && cId != null, Goods::getCId, cId);
        List<Goods> list = this.list(wrapper);
        if(list.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        ArrayList<Integer> res = new ArrayList<>();
        for (Goods goods : list) res.add(goods.getId());
        if(res.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode());
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 修改商品
     * @param updateGoodsVo
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateGoodsById(UpdateGoodsVo updateGoodsVo) {
        Goods goods = new Goods();
        goods.setId(updateGoodsVo.getId());
        goods.setName(updateGoodsVo.getName());
        goods.setDetails(updateGoodsVo.getDetails());
        goods.setCId(updateGoodsVo.getCId());
        goods.setPrice(updateGoodsVo.getPrice());
        boolean b = this.updateById(goods);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }
}




