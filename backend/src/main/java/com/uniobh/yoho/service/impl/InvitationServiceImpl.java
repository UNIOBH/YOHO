package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.RecommendEnum;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.Invitation;
import com.uniobh.yoho.pojo.User;
import com.uniobh.yoho.service.CommentService;
import com.uniobh.yoho.service.InvitationLikeService;
import com.uniobh.yoho.service.InvitationService;
import com.uniobh.yoho.mapper.InvitationMapper;
import com.uniobh.yoho.service.UserService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_invitation】的数据库操作Service实现
* @createDate 2022-10-03 21:06:09
*/
@Service
@Slf4j
public class InvitationServiceImpl extends ServiceImpl<InvitationMapper, Invitation>
    implements InvitationService{

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private InvitationLikeService invitationLikeService;

    /**
     * 根据帖子id查询
     * @param id 帖子id
     * @return
     */
    @Override
    public ResultUtil<InvitationAndCommentVo> getInvitationById(Integer id) {
        if(id != null && id != 0) {
            Invitation invitation = this.getById(id);
            if(invitation != null) {
                InvitationAndCommentVo vo = new InvitationAndCommentVo();
                vo.setIId(invitation.getId());
                vo.setTitle(invitation.getTitle());
                vo.setDetails(invitation.getDetails());
                vo.setCreateTime(invitation.getCreateTime());
                vo.setIRead(invitation.getReadCount());
                vo.setLike(invitation.getLikeCount());
                vo.setLastTime(invitation.getLastTime());
                User user = userService.getUserById(invitation.getUId()).getData();
                User u = new User();
                if(user != null) {
                    u.setId(user.getId());
                    u.setAvatar(user.getAvatar());
                    u.setNickname(user.getNickname());
                }
                vo.setUser(u);
                List<CommentVo> commentVos = commentService.getCommentByIId(id).getData();
                vo.setComments(commentVos);
                vo.setCTotal(commentService.getCommentTotalByIId(id).getData());
                return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), vo);
            }
        }
        return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
    }


    /**
     * 分页查询
     * @param current 当前页数
     * @param num 一页多少条数据
     * @param sId 学校id
     * @param recommendEnum 是否推荐
     * @param keyword 关键字
     * @param uId 用户id
     * @return
     */
    @Override
    public ResultUtil<Map<String, Object>> getInvitationByPage(Integer current, Integer num, Integer sId,
                                                               RecommendEnum recommendEnum, String keyword,
                                                               Integer uId) {
        List<Integer> integers = null;
        if(sId != 0 && sId != null) integers = userService.getUIdsBySId(sId).getData();
        LambdaQueryWrapper<Invitation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(recommendEnum != null, Invitation::getIsRecommend, recommendEnum)
                .eq(uId != 0, Invitation::getUId, uId)
                .and(keyword != null && !("".equals(keyword)),
                        i -> i.like(Invitation::getTitle, keyword)
                                .or()
                                .like(Invitation::getDetails, keyword))
                .in(integers != null, Invitation::getUId, integers);
        Page<Invitation> page = new Page<>(current, num);
        Page<Invitation> invitationPage = this.page(page, queryWrapper);
        List<Invitation> records = invitationPage.getRecords(); // 获取page里的记录
        long total = invitationPage.getPages();
        if(total == 0 || records.size() == 0 || records == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        HashMap<String, Object> res = new HashMap<>();
        res.put("total", total);
        ArrayList<InvitationDetailsVo> list = new ArrayList<>();
        for (Invitation record : records) {
            InvitationDetailsVo vo = new InvitationDetailsVo();
            vo.setId(record.getId());
            vo.setDetails(record.getDetails());
            vo.setTitle(record.getTitle());
            vo.setCreateTime(record.getCreateTime());
            vo.setReadCount(record.getReadCount());
            vo.setLikeCount(record.getLikeCount());
            vo.setLastTime(record.getLastTime());
            vo.setRecommend(record.getIsRecommend().getRecommendCode());
            ResultUtil<User> user = userService.getUserById(record.getUId());
            if(user != null) {
                User u = new User();
                u.setId(user.getData().getId());
                u.setAvatar(user.getData().getAvatar());
                u.setNickname(user.getData().getNickname());
                vo.setUser(u);
            }
            Long data = commentService.getCommentTotalByIId(record.getId()).getData();
            vo.setTotalComment(data);
            list.add(vo);
        }
        res.put("list", list);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 根据帖子id删除帖子
     * @param ids 由帖子id构成的集合
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateDel(List<Integer> ids) {
        boolean b = false;
        if(ids.size() != 0 && ids != null) b = this.removeBatchByIds(ids);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 根据帖子id修改推荐
     * @param ids 帖子id
     * @param recommendEnum 是否推荐
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateRecommend(List<Integer> ids, RecommendEnum recommendEnum) {
        boolean b = false;
        if(ids.size() != 0 && ids != null) {
            ArrayList<Invitation> invitations = new ArrayList<>();
            for (Integer id : ids) {
                Invitation invitation = new Invitation();
                invitation.setId(id);
                invitation.setIsRecommend(recommendEnum);
                invitations.add(invitation);
            }
            if(invitations.size() != 0 && invitations != null) b = this.updateBatchById(invitations);
        }
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 根据帖子id增加阅读量
     * @param id
     * @return
     */
    @Override
    public ResultUtil<Boolean> addReadCountById(Integer id) {
        Invitation invitation = this.getById(id);
        if(invitation == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode());
        invitation.setReadCount(invitation.getReadCount() + 1);
        boolean b = this.updateById(invitation);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 根据用户id查询
     * @param uId
     * @return
     */
    @Override
    public ResultUtil<List<Invitation>> getInvitationsByUId(Integer uId) {
        LambdaQueryWrapper<Invitation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(uId != null && uId != 0, Invitation::getUId, uId);
        List<Invitation> list = this.list(wrapper);
        if(list.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), list);
    }

    /**
     * 添加帖子
     * @param addInvitationVo
     * @return
     */
    @Override
    public ResultUtil<Boolean> addInvitationByAddInvitation(AddInvitationVo addInvitationVo) {
        if(addInvitationVo == null) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "参数为空");
        Invitation invitation = new Invitation();
        invitation.setDetails(addInvitationVo.getDetails());
        invitation.setTitle(addInvitationVo.getTitle());
        invitation.setUId(addInvitationVo.getUId());
        boolean b = this.save(invitation);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 根据帖子id获取帖子信息
     * @param id
     * @return
     */
    @Override
    public ResultUtil<Invitation> getInvitation(Integer id) {
        Invitation invitation = this.getById(id);
        if(invitation == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode());
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), invitation);
    }

    /**
     * 根据帖子id修改帖子内容
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateInvitationById(UpdateContentInvitationVo vo) {
        Invitation invitation = new Invitation();
        invitation.setId(vo.getId());
        invitation.setDetails(vo.getContent());
        invitation.setTitle(vo.getTitle());
        boolean b = this.updateById(invitation);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 增加帖子点赞量
     * @param id 帖子id
     * @param uId 用户id
     * @return
     */
    @Override
    public ResultUtil<Boolean> addLike(Integer id, Integer uId) {
        Boolean b = invitationLikeService.isLike(id, uId).getData();
        if(b) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "已经点赞过了");
        Invitation invitation = this.getById(id);
        invitation.setLikeCount(invitation.getLikeCount() == null ? 0 + 1 : invitation.getLikeCount() + 1);
        boolean update = this.updateById(invitation);
        if(!update) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "修改失败");
        Boolean data = invitationLikeService.addInvitationLike(id, uId).getData();
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode());
    }

    /**
     * 根据用户id查询用户喜欢的帖子
     * @param uId 用户id
     * @return
     */
    @Override
    public ResultUtil<List<Invitation>> getInvitationByUIdLike(Integer uId) {
        List<Integer> list = invitationLikeService.getIIdsByUId(uId).getData();
        if(list == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode());
        ArrayList<Invitation> res = new ArrayList<>();
        for (Integer integer : list) {
            Invitation invitation = this.getById(integer);
            res.add(invitation);
        }
        if(res.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode());
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }
}




