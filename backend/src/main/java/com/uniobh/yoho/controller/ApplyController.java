package com.uniobh.yoho.controller;

import com.uniobh.yoho.enums.ApplyStateEnum;
import com.uniobh.yoho.pojo.Apply;
import com.uniobh.yoho.service.ApplyService;
import com.uniobh.yoho.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @PostMapping("/apply")
    public ResultUtil<Apply> addApply(Integer pId, Integer cId, String sName) {
        return applyService.addApplyByPIdAndCIdAndCname(pId, cId, sName);
    }

    @GetMapping("/apply/{current}/{num}")
    public ResultUtil<Map<String, Object>> getApplyByPage(@PathVariable("current") Integer current,
                                                          @PathVariable("num") Integer num) {
        return applyService.getApplyByPage(current, num);
    }

    @PutMapping("/apply/{cId}/{id}/{name}/{state}")
    public ResultUtil<Boolean> updateState(@PathVariable("cId") Integer cId, @PathVariable("id") Integer id,
                                           @PathVariable("name") String name, @PathVariable("state") Integer state) {
        return applyService.updateState(cId, id, name, state == 1 ? ApplyStateEnum.AGREE : ApplyStateEnum.REFUSE);
    }
}
