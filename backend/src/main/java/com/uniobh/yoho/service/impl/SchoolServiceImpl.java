package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.City;
import com.uniobh.yoho.pojo.Province;
import com.uniobh.yoho.pojo.School;
import com.uniobh.yoho.service.CityService;
import com.uniobh.yoho.service.SchoolService;
import com.uniobh.yoho.mapper.SchoolMapper;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.SearchSchoolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_school】的数据库操作Service实现
* @createDate 2022-10-03 21:12:48
*/
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School>
    implements SchoolService{

    @Autowired
    private CityService cityService;

    /**
     * 根据城市id查询学校
     * @param cId 城市id
     * @return
     */
    @Override
    public ResultUtil<List<School>> getSchoolByCityId(Integer cId) {
        LambdaQueryWrapper<School> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(School::getCId, cId);
        List<School> list = this.list(wrapper);
        if(list == null || list.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), list);
    }

    /**
     * 添加学校
     * @param cId 城市id
     * @param name 学校名称
     * @return
     */
    @Override
    public ResultUtil<Boolean> addSchoolByCIdAndName(Integer cId, String name) {
        School school = new School();
        school.setCId(cId);
        school.setName(name);
        boolean b = this.save(school);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), b);
    }

    /**
     * 分页查询
     * @param current 当前页
     * @param num 一页多少条数据
     * @return
     */
    @Override
    public ResultUtil<Map<String, Object>> getSchoolByPage(Integer current, Integer num) {
        Page<School> page = new Page<>(current, num);
        Page<School> schoolPage = this.page(page);
        List<School> records = schoolPage.getRecords();
        long total = schoolPage.getPages();
        if(total == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        ArrayList<SearchSchoolVo> vos = new ArrayList<>();
        for (School record : records) {
            SearchSchoolVo searchSchoolVo = new SearchSchoolVo();
            searchSchoolVo.setId(record.getId());
            searchSchoolVo.setSName(record.getName());
            Map<String, Object> data = cityService.getCityAndProvinceById(record.getCId()).getData();
            if(data != null)  {
                searchSchoolVo.setPName(((Province)data.get("province")).getName());
                searchSchoolVo.setCName(((City)data.get("city")).getName());
            }
            vos.add(searchSchoolVo);
        }
        if(vos.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        map.put("list", vos);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), map);
    }

    /**
     * 根据学校id查询
     * @param id 学校id
     * @return
     */
    @Override
    public ResultUtil<School> getSchoolById(Integer id) {
        School school = this.getById(id);
        if(school == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), school);
    }

    /**
     * 获取所有学校
     * @return
     */
    @Override
    public ResultUtil<List<School>> getAll() {
        List<School> list = this.list();
        if(list.size() == 0 || list == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode());
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), list);
    }

}




