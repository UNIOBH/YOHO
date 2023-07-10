package com.uniobh.yoho.controller;

import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.GoodsPicture;
import com.uniobh.yoho.service.GoodsPictureService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.UpdateGoodsImgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GoodsPictureController {
    @Autowired
    private GoodsPictureService goodsPictureService;

    @PostMapping("/goods-picture/main/{uId}/{gId}")
    public ResultUtil<Boolean> uploadMainImg(@RequestParam("mainImg") MultipartFile file, @PathVariable("uId") Integer uId,
                                      @PathVariable("gId") Integer gId) {
        return goodsPictureService.uploadMainImg(file, uId, gId);
    }

    @PostMapping("/goods-picture/{uId}/{gId}")
    public ResultUtil<Boolean> uploadImgs(@RequestParam("imgs") MultipartFile[] files,
                                          @PathVariable("uId") Integer uId,
                                          @PathVariable("gId") Integer gId) {
        return goodsPictureService.uploadImgs(files, uId, gId);
    }

    @GetMapping("/goods-picture/{gId}")
    public ResultUtil<List<GoodsPicture>> getAllByGId(@PathVariable("gId") Integer gId) {
        ArrayList<GoodsPicture> res = new ArrayList<>();
        GoodsPicture data = goodsPictureService.getGoodsPictureByMain(gId).getData();
        if(data != null) res.add(data);
        List<GoodsPicture> list = goodsPictureService.getGoodsPictureByGId(gId).getData();
        if(list != null) for (GoodsPicture goodsPicture : list) res.add(goodsPicture);
        if(res == null || res.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    @PutMapping("/goods-picture/{id}")
    public ResultUtil<Boolean> updateState(@PathVariable("id") Integer id) {
        return goodsPictureService.updateStateById(id);
    }
//
//    @PutMapping("/goods-category/main")
//    public ResultUtil<Boolean> updateImgById(@RequestParam("mainImg") MultipartFile file,
//                                             @RequestBody UpdateGoodsImgVo updateGoodsImgVo) {
//        MultipartFile[] files = {file};
//        return goodsPictureService.updateImgById(files, updateGoodsImgVo);
//    }
//
//    @PutMapping("/goods-category/other")
//    public ResultUtil<Boolean> updateImgById(@RequestParam("img") MultipartFile[] files,
//                                             @RequestBody UpdateGoodsImgVo updateGoodsImgVo) {
//        return goodsPictureService.updateImgById(files, updateGoodsImgVo);
//    }
}