package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.CommentLike;
import com.uniobh.yoho.pojo.InvitationLike;
import com.uniobh.yoho.service.InvitationLikeService;
import com.uniobh.yoho.mapper.InvitationLikeMapper;
import com.uniobh.yoho.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 23249
* @description 针对表【t_invitation_like】的数据库操作Service实现
* @createDate 2022-10-05 19:49:59
*/
@Service
public class InvitationLikeServiceImpl extends ServiceImpl<InvitationLikeMapper, InvitationLike>
    implements InvitationLikeService{

    /**
     * 判断该用户是否给帖子点过赞
     * @param iId
     * @param uId
     * @return
     */
    @Override
    public ResultUtil<Boolean> isLike(Integer iId, Integer uId) {
        LambdaQueryWrapper<InvitationLike> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InvitationLike::getIId, iId).eq(InvitationLike::getUId, uId);
        InvitationLike invitationLike = this.getOne(wrapper);
        if(invitationLike != null) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), true); // 点过赞
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), false);   // 没有点过赞
    }

    /**
     * 添加
     * @param iId
     * @param uId
     * @return
     */
    @Override
    public ResultUtil<Boolean> addInvitationLike(Integer iId, Integer uId) {
        InvitationLike invitationLike = new InvitationLike();
        invitationLike.setIId(iId);
        invitationLike.setUId(uId);
        boolean save = this.save(invitationLike);
        if(save) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), save);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), save);
    }

    /**
     * 根据用户id获取用户喜欢的帖子id
     * @param uId
     * @return
     */
    @Override
    public ResultUtil<List<Integer>> getIIdsByUId(Integer uId) {
        LambdaQueryWrapper<InvitationLike> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InvitationLike::getUId, uId);
        List<InvitationLike> list = this.list(wrapper);
        if(list.size() == 0 || list == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        ArrayList<Integer> res = new ArrayList<>();
        for (InvitationLike invitationLike : list) res.add(invitationLike.getIId());
        if(res.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }
}




