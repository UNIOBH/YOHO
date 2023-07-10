package com.uniobh.yoho.service;

import com.uniobh.yoho.pojo.InvitationLike;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;

import java.util.List;

/**
* @author 23249
* @description 针对表【t_invitation_like】的数据库操作Service
* @createDate 2022-10-05 19:49:59
*/
public interface InvitationLikeService extends IService<InvitationLike> {

    ResultUtil<Boolean> isLike(Integer iId, Integer uId);

    ResultUtil<Boolean> addInvitationLike(Integer iId, Integer uId);

    ResultUtil<List<Integer>> getIIdsByUId(Integer uId);
}
