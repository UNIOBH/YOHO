package com.uniobh.yoho.service;

import com.uniobh.yoho.pojo.GoodsPicture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.UpdateGoodsImgVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author 23249
* @description 针对表【t_goods_picture】的数据库操作Service
* @createDate 2022-10-03 21:04:55
*/
public interface GoodsPictureService extends IService<GoodsPicture> {
    ResultUtil<GoodsPicture> getGoodsPictureByMain(Integer goodsId);

    ResultUtil<List<GoodsPicture>> getGoodsPictureByGId(Integer goodsId);

    ResultUtil<Boolean> uploadMainImg(MultipartFile file, Integer uId, Integer gId);

    ResultUtil<Boolean> uploadImgs(MultipartFile[] files, Integer uId, Integer gId);

    ResultUtil<Boolean> updateStateById(Integer id);

//    ResultUtil<Boolean> updateImgById(MultipartFile[] files, UpdateGoodsImgVo updateGoodsImgVo);
}
