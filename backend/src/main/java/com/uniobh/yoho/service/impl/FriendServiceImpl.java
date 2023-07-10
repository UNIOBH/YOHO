package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.FriendStateEnum;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.pojo.Friend;
import com.uniobh.yoho.pojo.User;
import com.uniobh.yoho.service.FriendService;
import com.uniobh.yoho.mapper.FriendMapper;
import com.uniobh.yoho.service.UserService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddFriendVo;
import com.uniobh.yoho.vo.FriendApplyVo;
import com.uniobh.yoho.vo.GetFriendVo;
import com.uniobh.yoho.vo.UpdateRemakeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 23249
* @description 针对表【t_friend】的数据库操作Service实现
* @createDate 2022-10-03 20:50:54
*/
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
    implements FriendService{

    @Autowired
    private UserService userService;

    /**
     * 发送好友验证
     * @param friendVo
     * @return
     */
    @Override
    public ResultUtil<AddFriendVo> addFriendByUIdAndFUId(AddFriendVo friendVo) {
        Friend friend = new Friend();
        friend.setUId(friendVo.getUId());
        friend.setFriendUserId(friendVo.getFUId());
        friend.setVerificationInformation(friendVo.getMsg());
        friend.setState(FriendStateEnum.VERIFYING);
        boolean b = this.save(friend);
        if(b == false) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode());
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), friendVo);
    }

    /**
     * 根据用户id和关键字查询好友
     * @param uId 用户id
     * @param keyword 关键字
     * @return
     */
    @Override
    public ResultUtil<List<GetFriendVo>> getFriendByUId(Integer uId, String keyword) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(uId != 0 && uId != null, Friend::getUId, uId)
                .eq(Friend::getState, FriendStateEnum.ALREADY);
        List<Friend> list = this.list(wrapper);
        if(list.size() == 0 || list == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        ArrayList<GetFriendVo> res = new ArrayList<>();
        for (Friend friend : list) {
            User user = userService.getUserById(friend.getFriendUserId()).getData();
            GetFriendVo friendVo = new GetFriendVo();
            if(user != null) {
                User u = new User();
                u.setId(user.getId());
                u.setNickname(user.getNickname());
                u.setAvatar(user.getAvatar());
                friendVo.setFriend(u);
            }
            // 如果关键字存在，则判断用户昵称和备注是否包含关键字秒，如果不包含则这个friend不add res里，如果包含则add
            if(keyword != null
                    && !("".equals(keyword))
                    && friendVo.getFriend() != null
                    && friendVo.getFriend().getNickname().indexOf(keyword) == -1
                    && friend.getRemark() != null
                    && friend.getRemark().indexOf(keyword) == -1) continue;
            friendVo.setId(friend.getId());
            friendVo.setRemake(friend.getRemark());
            res.add(friendVo);
        }
        if(res.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 根据id修改好友状态
     * @param id
     * @param friendStateEnum
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateStateById(Integer id, FriendStateEnum friendStateEnum) {
        Friend friend = new Friend();
        friend.setId(id);
        if(friendStateEnum != null) friend.setState(friendStateEnum);
        if(friendStateEnum == FriendStateEnum.ALREADY) {    // 同意好友申请，增加数据
            Friend data = getFriendById(id).getData();  // 获取当前好友表数据
            if(data != null) addFriend(data.getFriendUserId(), data.getUId());  // 颠倒用户id和好友id
        }
        boolean b = this.updateById(friend);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 删除好友
     * @param id
     * @return
     */
    @Override
    public ResultUtil<Boolean> delFriendById(Integer id) {
        boolean b = this.removeById(id);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 根据用户id和用户好友id增加
     * @param uId 用户id
     * @param fId 用户好友id
     * @return
     */
    @Override
    public ResultUtil<Boolean> addFriend(Integer uId, Integer fId) {
        Friend friend = new Friend();
        friend.setUId(uId);
        friend.setFriendUserId(fId);
        friend.setState(FriendStateEnum.ALREADY);
        boolean b = this.save(friend);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public ResultUtil<Friend> getFriendById(Integer id) {
        Friend friend = this.getById(id);
        if(friend == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), friend);
    }

    /**
     * 根据用户id查询正在验证的好友
     * @param id
     * @return
     */
    @Override
    public ResultUtil<List<FriendApplyVo>> getFriendsInVerificationById(Integer id) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(id != 0 && id != null, Friend::getFriendUserId, id).eq(Friend::getState, FriendStateEnum.VERIFYING);
        List<Friend> list = this.list(wrapper);
        ArrayList<FriendApplyVo> res = new ArrayList<>();
        for (Friend friend : list) {
            FriendApplyVo friendApplyVo = new FriendApplyVo();
            friendApplyVo.setId(friend.getId());
            friendApplyVo.setVerification(friend.getVerificationInformation());
            User user = userService.getUserById(friend.getFriendUserId()).getData();
            if(user != null) {
                User u = new User();
                u.setId(user.getId());
                u.setAvatar(user.getAvatar());
                u.setNickname(user.getNickname());
                friendApplyVo.setFriend(u);
            }
            res.add(friendApplyVo);
        }
        if(res.size() == 0) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 根据本人id和用户id判断双方是否是好友
     * @param uId
     * @param fId
     * @return
     */
    @Override
    public ResultUtil<Boolean> isFriendByUIdAndFId(Integer uId, Integer fId) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(uId != null && uId != 0, Friend::getUId, uId)
                .eq(fId != null && fId != 0, Friend::getFriendUserId, fId);
        Friend friend = this.getOne(wrapper);
        if(friend == null) return ResultUtil.success("no", false);
        return ResultUtil.success("yes", true);
    }

    /**
     * 根据id修改好友备注
     * @param updateRemakeVo
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateRemakeById(UpdateRemakeVo updateRemakeVo) {
        Friend friend = new Friend();
        friend.setId(updateRemakeVo.getId());
        friend.setRemark(updateRemakeVo.getRemake());
        boolean b = this.updateById(friend);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode());
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }


}




