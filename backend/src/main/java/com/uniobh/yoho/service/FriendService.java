package com.uniobh.yoho.service;

import com.uniobh.yoho.enums.FriendStateEnum;
import com.uniobh.yoho.pojo.Friend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddFriendVo;
import com.uniobh.yoho.vo.FriendApplyVo;
import com.uniobh.yoho.vo.GetFriendVo;
import com.uniobh.yoho.vo.UpdateRemakeVo;

import java.util.List;

/**
* @author 23249
* @description 针对表【t_friend】的数据库操作Service
* @createDate 2022-10-03 20:50:54
*/
public interface FriendService extends IService<Friend> {
    ResultUtil<AddFriendVo> addFriendByUIdAndFUId(AddFriendVo friendVo);

    ResultUtil<List<GetFriendVo>> getFriendByUId(Integer uId, String keyword);

    ResultUtil<Boolean> updateStateById(Integer id, FriendStateEnum friendStateEnum);

    ResultUtil<Boolean> delFriendById(Integer id);

    ResultUtil<Boolean> addFriend(Integer uId, Integer fId);

    ResultUtil<Friend> getFriendById(Integer id);

    ResultUtil<List<FriendApplyVo>> getFriendsInVerificationById(Integer id);

    ResultUtil<Boolean> isFriendByUIdAndFId(Integer uId, Integer fId);

    ResultUtil<Boolean> updateRemakeById(UpdateRemakeVo updateRemakeVo);
}
