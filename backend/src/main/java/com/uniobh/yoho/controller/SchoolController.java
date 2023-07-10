package com.uniobh.yoho.controller;

import com.uniobh.yoho.pojo.School;
import com.uniobh.yoho.service.SchoolService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddSchoolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/school/{cId}")
    public ResultUtil<List<School>> getSchoolByCId(@PathVariable("cId") Integer cId) {
        return schoolService.getSchoolByCityId(cId);
    }

    @PostMapping("/school")
    public ResultUtil<Boolean> addSchoolByCIdAndName(@RequestBody AddSchoolVo addSchoolVo) {
        return schoolService.addSchoolByCIdAndName(addSchoolVo.getCId(), addSchoolVo.getName());
    }

    @GetMapping("/school/{current}/{num}")
    public ResultUtil<Map<String, Object>> getSchoolByPage(@PathVariable("current") Integer current,
                                                           @PathVariable("num") Integer num) {
        return schoolService.getSchoolByPage(current, num);
    }

    /**
     * 获取所有学校
     * @return
     */
    @GetMapping("/school")
    public ResultUtil<List<School>> getAll() {
        return schoolService.getAll();
    }
}
