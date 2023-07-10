package com.uniobh.yoho.service;

import com.uniobh.yoho.enums.RecommendEnum;
import com.uniobh.yoho.pojo.Invitation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddInvitationVo;
import com.uniobh.yoho.vo.InvitationAndCommentVo;
import com.uniobh.yoho.vo.UpdateContentInvitationVo;

import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_invitation】的数据库操作Service
* @createDate 2022-10-03 21:06:09
*/
public interface InvitationService extends IService<Invitation> {
    ResultUtil<InvitationAndCommentVo> getInvitationById(Integer id);

    ResultUtil<Map<String, Object>> getInvitationByPage(Integer current, Integer num, Integer sId,
                                        RecommendEnum recommendEnum, String keyword, Integer uId);

    ResultUtil<Boolean> updateDel(List<Integer> ids);

    ResultUtil<Boolean> updateRecommend(List<Integer> ids, RecommendEnum recommendEnum);

    ResultUtil<Boolean> addReadCountById(Integer id);

    ResultUtil<List<Invitation>> getInvitationsByUId(Integer uId);

    ResultUtil<Boolean> addInvitationByAddInvitation(AddInvitationVo addInvitationVo);

    ResultUtil<Invitation> getInvitation(Integer id);

    ResultUtil<Boolean> updateInvitationById(UpdateContentInvitationVo vo);

    ResultUtil<Boolean> addLike(Integer id, Integer uId);

    ResultUtil<List<Invitation>> getInvitationByUIdLike(Integer uId);
}
