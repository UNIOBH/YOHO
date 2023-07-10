package com.uniobh.yoho.controller;

import com.uniobh.yoho.enums.FriendStateEnum;
import com.uniobh.yoho.pojo.Friend;
import com.uniobh.yoho.service.FriendService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddFriendVo;
import com.uniobh.yoho.vo.FriendApplyVo;
import com.uniobh.yoho.vo.GetFriendVo;
import com.uniobh.yoho.vo.UpdateRemakeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendController {
    @Autowired
    private FriendService friendService;

    @PostMapping("/friend")
    public ResultUtil<AddFriendVo> addFriend(@RequestBody AddFriendVo friendVo) {
        return friendService.addFriendByUIdAndFUId(friendVo);
    }

    @GetMapping("/friend/{keyword}/{uId}")
    public ResultUtil<List<GetFriendVo>> getFriendByUId(@PathVariable("uId") Integer uId,
                                                        @PathVariable("keyword") String keyword) {
        return friendService.getFriendByUId(uId, " ".equals(keyword) ? null : keyword);
    }

    @PutMapping("/friend/{id}/{state}")
    public ResultUtil<Boolean> updateStateById(@PathVariable("id") Integer id, @PathVariable("state") Integer state) {
        FriendStateEnum friendStateEnum = null;
        if(state == FriendStateEnum.ALREADY.getState()) friendStateEnum = FriendStateEnum.ALREADY;
        else if(state == FriendStateEnum.REFUSE.getState()) friendStateEnum = FriendStateEnum.REFUSE;
        return friendService.updateStateById(id, friendStateEnum);
    }

    @DeleteMapping("/friend/{id}")
    public ResultUtil<Boolean> delFriendById(@PathVariable("id") Integer id) {
        return friendService.delFriendById(id);
    }

    @GetMapping("/friend/{id}")
    public ResultUtil<List<FriendApplyVo>> getFriendsInVerificationById(@PathVariable("id") Integer id) {
        return friendService.getFriendsInVerificationById(id);
    }

    @GetMapping("/friend/is/{uId}/{fId}")
    public ResultUtil<Boolean> isFriendByUIdAndFId(@PathVariable("uId") Integer uId, @PathVariable("fId") Integer fId) {
        return friendService.isFriendByUIdAndFId(uId, fId);
    }

    @PutMapping("/friend")
    public ResultUtil<Boolean> updateRemakeById(@RequestBody UpdateRemakeVo updateRemakeVo) {
        return friendService.updateRemakeById(updateRemakeVo);
    }
}
