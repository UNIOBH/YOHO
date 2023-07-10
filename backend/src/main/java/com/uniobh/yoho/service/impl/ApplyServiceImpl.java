package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.ApplyStateEnum;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.Apply;
import com.uniobh.yoho.pojo.City;
import com.uniobh.yoho.pojo.Province;
import com.uniobh.yoho.service.ApplyService;
import com.uniobh.yoho.mapper.ApplyMapper;
import com.uniobh.yoho.service.CityService;
import com.uniobh.yoho.service.ProvinceService;
import com.uniobh.yoho.service.SchoolService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.ApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_apply】的数据库操作Service实现
* @createDate 2022-11-03 16:51:11
*/
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply>
    implements ApplyService{

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private SchoolService schoolService;

    /**
     * 添加申请
     * @param pId 省份id
     * @param cId 城市id
     * @param sName 学校名称
     * @return
     */
    @Override
    public ResultUtil<Apply> addApplyByPIdAndCIdAndCname(Integer pId, Integer cId, String sName) {
        Apply apply = new Apply();
        apply.setPId(pId);
        apply.setCId(cId);
        apply.setSName(sName);
        boolean save = this.save(apply);
        if(save) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), apply);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 分页查询未处理的申请
     * @param current 当前页
     * @param num 一页多少条数据
     * @return
     */
    @Override
    public ResultUtil<Map<String, Object>> getApplyByPage(Integer current, Integer num) {
        Page<Apply> page = new Page<>(current, num);
        LambdaQueryWrapper<Apply> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Apply::getState, 2);
        Page<Apply> applyPage = this.page(page, wrapper);
        List<Apply> records = applyPage.getRecords();
        long total = applyPage.getPages();
        if(records != null && records.size() != 0) {
            HashMap<String, Object> res = new HashMap<>();
            res.put("total", total);
            ArrayList<ApplyVo> applyVos = new ArrayList<>();
            for (Apply record : records) {
                ApplyVo applyVo = new ApplyVo();
                Province p = provinceService.getProvinceById(record.getPId()).getData();
                applyVo.setProvince(p);
                City c  = cityService.getNameById(record.getCId()).getData();
                applyVo.setCity(c);
                applyVo.setId(record.getId());
                applyVo.setName(record.getSName());
                applyVos.add(applyVo);
            }
            res.put("list", applyVos);
            if(applyVos.size() != 0) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
        }
        return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
    }

    /**
     * 根据id修改状态
     * @param cId 城市id
     * @param id
     * @param name 学校名称
     * @param state
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateState(Integer cId, Integer id, String name, ApplyStateEnum state) {
        Apply apply = new Apply();
        apply.setId(id);
        apply.setState(state);
        boolean b = this.updateById(apply);
        if(state.getCode() == ApplyStateEnum.AGREE.getCode()) {
            Integer status = schoolService.addSchoolByCIdAndName(cId, name).getStatus();
            if(status == ResultStatusEnum.PERFECT.getCode()) b = true;
            else b = false;
        }
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }
}




