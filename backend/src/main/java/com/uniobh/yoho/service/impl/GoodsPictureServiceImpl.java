package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.FileTypeEnum;
import com.uniobh.yoho.enums.GoodsMainPictureEnum;
import com.uniobh.yoho.enums.PictureStateEnum;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.GoodsPicture;
import com.uniobh.yoho.service.GoodsPictureService;
import com.uniobh.yoho.mapper.GoodsPictureMapper;
import com.uniobh.yoho.utils.ImageUtil;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.UpdateGoodsImgVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
* @author 23249
* @description 针对表【t_goods_picture】的数据库操作Service实现
* @createDate 2022-10-03 21:04:55
*/
@Service
public class GoodsPictureServiceImpl extends ServiceImpl<GoodsPictureMapper, GoodsPicture>
    implements GoodsPictureService{

    /**
     * 根据商品id查询主图
     * @param goodsId 商品id
     * @return
     */
    @Override
    public ResultUtil<GoodsPicture> getGoodsPictureByMain(Integer goodsId) {
        LambdaQueryWrapper<GoodsPicture> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsPicture::getIsMain, GoodsMainPictureEnum.YES).eq(GoodsPicture::getGId, goodsId).eq(GoodsPicture::getState, PictureStateEnum.QUALIFIED);
        GoodsPicture goodsPicture = this.getOne(queryWrapper);
        if(goodsPicture == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        goodsPicture.setImg(ImageUtil.SERVER_PATH + goodsPicture.getImg());
        if(goodsPicture == null || goodsPicture.getImg() == null || "".equals(goodsPicture.getImg())) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), goodsPicture);
    }

    /**
     * 根据商品id查询商品图片
     * @param goodsId 商品id
     * @return
     */
    @Override
    public ResultUtil<List<GoodsPicture>> getGoodsPictureByGId(Integer goodsId) {
        LambdaQueryWrapper<GoodsPicture> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsPicture::getGId, goodsId).eq(GoodsPicture::getState, PictureStateEnum.QUALIFIED);
        List<GoodsPicture> list = this.list(queryWrapper);
        if(list == null || list.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        for (GoodsPicture picture : list) picture.setImg(ImageUtil.SERVER_PATH + picture.getImg());
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), list);
    }

    /**
     * 上传商品主图
     * @param file
     * @param uId 用户id
     * @param gId 商品id
     * @return
     */
    @Override
    public ResultUtil<Boolean> uploadMainImg(MultipartFile file, Integer uId, Integer gId) {
        MultipartFile[] files = {file};
        List<String> list = ImageUtil.upload(files, FileTypeEnum.GOODS, uId);  // 上传成功后的图片路径
        if(list == null || list.size() == 0) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "上传失败");
        GoodsPicture goodsPicture = new GoodsPicture();
        goodsPicture.setImg(list.get(0));
        goodsPicture.setGId(gId);
        goodsPicture.setIsMain(GoodsMainPictureEnum.YES);
        goodsPicture.setState(PictureStateEnum.QUALIFIED);
        boolean b = this.save(goodsPicture);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 上传商品其他图片
     * @param files
     * @param uId
     * @param gId
     * @return
     */
    @Override
    public ResultUtil<Boolean> uploadImgs(MultipartFile[] files, Integer uId, Integer gId) {
        List<String> strings = ImageUtil.upload(files, FileTypeEnum.GOODS, uId);
        if(strings.size() == 0 || strings == null) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "上传失败！");
        ArrayList<GoodsPicture> goodsPictures = new ArrayList<>();
        for (String string : strings) {
            GoodsPicture goodsPicture = new GoodsPicture();
            goodsPicture.setImg(string);
            goodsPicture.setGId(gId);
            goodsPicture.setIsMain(GoodsMainPictureEnum.NO);
            goodsPicture.setState(PictureStateEnum.QUALIFIED);
            goodsPictures.add(goodsPicture);
        }
        if(goodsPictures == null || goodsPictures.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "无数据");
        boolean b = this.saveBatch(goodsPictures);
        if(b) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "上传失败！");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
    }

    /**
     * 根据id修改状态
     * @param id 商品图片id
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateStateById(Integer id) {
        GoodsPicture goodsPicture = new GoodsPicture();
        goodsPicture.setId(id);
        goodsPicture.setImg(FileTypeEnum.GOODS + "暂无图片.png");
        goodsPicture.setState(PictureStateEnum.VIOLATION);
        boolean b = this.updateById(goodsPicture);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), "修改成功！", b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "修改失败！");
    }

    /**
     * 根据id修改图片路径
     * @param updateGoodsImgVo
     * @return
     */
//    @Override
//    public ResultUtil<Boolean> updateImgById(MultipartFile[] files, UpdateGoodsImgVo updateGoodsImgVo) {
//        List<String> upload = ImageUtil.upload(files, FileTypeEnum.GOODS, updateGoodsImgVo.getUId());
//        if(upload == null) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "上传失败");
//        GoodsPicture goodsPicture = new GoodsPicture();
//        goodsPicture.setId(updateGoodsImgVo.getId());
//        goodsPicture.setImg(upload.get(0));
//        boolean b = this.updateById(goodsPicture);
//        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
//        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
//    }

}
