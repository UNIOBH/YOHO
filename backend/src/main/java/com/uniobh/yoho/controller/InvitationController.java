package com.uniobh.yoho.controller;

import com.uniobh.yoho.enums.FileTypeEnum;
import com.uniobh.yoho.enums.RecommendEnum;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.Invitation;
import com.uniobh.yoho.service.InvitationService;
import com.uniobh.yoho.utils.ImageUtil;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddInvitationVo;
import com.uniobh.yoho.vo.InvitationAndCommentVo;
import com.uniobh.yoho.vo.UpdateContentInvitationVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class InvitationController {
    @Autowired
    private InvitationService invitationService;

    @GetMapping("/invitation/{current}/{num}/{sId}")
    public ResultUtil<Map<String, Object>> getInvitationByRecommend(@PathVariable("current") Integer current,
                                                    @PathVariable("num") Integer num,
                                                    @PathVariable("sId") Integer sId) {
        return invitationService.getInvitationByPage(current, num, sId, RecommendEnum.YES, null, 0);
    }

    /**
     * 根据帖子id查询帖子及相关评论
     * @param id 帖子id
     * @return
     */
    @GetMapping("/invitation/{id}")
    public ResultUtil<InvitationAndCommentVo> getInvitationById(@PathVariable("id") Integer id) {
        return invitationService.getInvitationById(id);
    }

    @GetMapping("/invitation/{current}/{num}/{keyword}/{sId}")
    public ResultUtil<Map<String, Object>> getInvitationByKeyword(@PathVariable("current") Integer current,
                                                                              @PathVariable("num") Integer num,
                                                                              @PathVariable("sId") Integer sId,
                                                                              @PathVariable("keyword") String keyword) {
        if (" ".equals(keyword)) keyword = null;
        return invitationService.getInvitationByPage(current, num, sId, null, keyword, 0);
    }

    @GetMapping("/invitation/{current}/{num}/{recommend}/{keyword}/{uId}")
    public ResultUtil<Map<String, Object>> getInvitationAdmin(@PathVariable("current") Integer current,
                                                         @PathVariable("num") Integer num,
                                                         @PathVariable("recommend") Integer recommend,
                                                         @PathVariable("keyword") String keyword,
                                                         @PathVariable("uId") Integer uId) {
        RecommendEnum recommendEnum = null;
        if(recommend == 1) recommendEnum = RecommendEnum.YES;
        else if(recommend == 0) recommendEnum = RecommendEnum.NO;
        if(" ".equals(keyword)) keyword = null;
        return invitationService.getInvitationByPage(current, num, 0, recommendEnum, keyword, uId);
    }

    @DeleteMapping("/invitation")
    public ResultUtil<Boolean> updateDel(@RequestBody List<Integer> ids) {
        return invitationService.updateDel(ids);
    }

    @PutMapping("/invitation/{recommend}")
    public ResultUtil<Boolean> updateRecommend(@RequestBody List<Integer> ids, @PathVariable("recommend") Integer recommend) {
        return invitationService.updateRecommend(ids, recommend == 1 ? RecommendEnum.YES : RecommendEnum.NO);
    }

    @PutMapping("/invitation")
    public ResultUtil<Boolean> addReadCount(@RequestBody Integer id) {
        return invitationService.addReadCountById(id);
    }

    @GetMapping("/invitation/profile/{uId}")
    public ResultUtil<List<Invitation>> getInvitationsByUId(@PathVariable("uId") Integer uId) {
        return invitationService.getInvitationsByUId(uId);
    }

    @PostMapping("/invitation")
    public ResultUtil<Boolean> addInvitationByAddInvitation(@RequestBody AddInvitationVo addInvitationVo) {
        return invitationService.addInvitationByAddInvitation(addInvitationVo);
    }

    /**
     * 上传帖子图片
     * @param file
     * @param uId
     * @return
     */
    @PostMapping("/invitation/img")
    public ResultUtil<String> uploadImg(@RequestParam("file") MultipartFile file,
                                              @RequestHeader("uId") Integer uId) {
        log.info("++++++++++++++++++++++++++++++++++++++++++" + uId + "++++++++++++++++++++++++++++++++++++++++++");
        MultipartFile[] files = {file};
        List<String> upload = ImageUtil.upload(files, FileTypeEnum.INVITATION, uId);
        String path = null;
        if(upload != null && upload.size() != 0) path = ImageUtil.SERVER_PATH + upload.get(0);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), "上传成功", path);
    }

    @GetMapping("/invitation/rough/{id}")
    public ResultUtil<Invitation> getInvitation(@PathVariable("id") Integer id) {
        return invitationService.getInvitation(id);
    }

    /**
     * 修改帖子内容
     * @return
     */
    @PutMapping("/invitation/content")
    public ResultUtil<Boolean> updateInvitationById(@RequestBody UpdateContentInvitationVo vo) {
        return invitationService.updateInvitationById(vo);
    }

    @PutMapping("/invitation/{id}/{uId}")
    public ResultUtil<Boolean> addLike(@PathVariable("id") Integer id, @PathVariable("uId") Integer uId) {
        return invitationService.addLike(id, uId);
    }

    @GetMapping("/invitation/favor/{uId}")
    public ResultUtil<List<Invitation>> getInvitationByUIdLike(@PathVariable("uId") Integer uId) {
        return invitationService.getInvitationByUIdLike(uId);
    }
}
