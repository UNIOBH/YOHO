package com.uniobh.yoho.controller;

import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.enums.UserSexEnum;
import com.uniobh.yoho.pojo.School;
import com.uniobh.yoho.pojo.User;
import com.uniobh.yoho.service.SchoolService;
import com.uniobh.yoho.service.UserService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SchoolService schoolService;

    /**
     * 登录
     * @param loginVo
     * @return
     */
    @PostMapping("/login")
    public ResultUtil<Map<String, Object>> login(@RequestBody LoginVo loginVo) {
        return userService.login(loginVo);
    }

    /**
     * 注册
     * @param registerVo
     * @return
     */
    @PostMapping("/register")
    public ResultUtil<User> register(@RequestBody RegisterVo registerVo) {
        return userService.register(registerVo);
    }

    /**
     * 检查邮箱是否已经注册过
     * @param email
     * @return
     */
    @GetMapping("/email/{email}")
    public ResultUtil<Boolean> checkEmail(@PathVariable("email") String email) {
        return userService.isEmailExist(email);
    }

    /** lbf
     * 根据用户id逻辑删除（注销）
     * @param uId
     * @return
     */
    @DeleteMapping("/user/{uId}")
    public ResultUtil<Boolean> deleteUserByUId(@PathVariable("uId") Integer uId){
        return userService.deleteUserByUId(uId);
    }

    @GetMapping("/user/{id}")
    public ResultUtil<GetUserVo> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id).getData();
        if(user == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "查无此人~");
        GetUserVo userVo = new GetUserVo();
        userVo.setName(user.getName());
        userVo.setNickname(user.getNickname());
        userVo.setSex(user.getSex().getSex() == 1 ? "男" : "女");
        userVo.setRegisterTime(user.getRegisterTime());
        userVo.setEmail(user.getEmail());
        userVo.setAvatar(user.getAvatar());
        School school = schoolService.getSchoolById(user.getSId()).getData();
        if(school != null) userVo.setSName(school.getName());
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), userVo);
    }

    /** lbf
     * 根据用户id，学校id，性别，关键字，一页多少条数据分页查询
     * @param current 当前页数
     * @param size 当前页有多少条数据
     * @param uId 用户id
     * @param sId 学校id
     * @param sex 性别
     * @param keyWord 关键字
     * @return
     */
    @GetMapping("/user/{current}/{size}/{uId}/{sId}/{keyWord}/{sex}")
    public ResultUtil<Map<String, Object>> selectUsersByPage(@PathVariable("current") Integer current, @PathVariable("size") Integer size, @PathVariable("uId") Integer uId, @PathVariable("sId") Integer sId, @PathVariable("sex") Integer sex, @PathVariable("keyWord") String keyWord) {
        UserSexEnum userSexEnum = null;
        if (sex == 1){
            userSexEnum = UserSexEnum.MALE;
        }
        if (sex == 0){
            userSexEnum = UserSexEnum.FEMALE;
        }
        if(" ".equals(keyWord)){
            keyWord = null;
        }
        return userService.selectUsersByPage(current, size, uId, sId, userSexEnum, keyWord);
    }


    @PostMapping("/admin")
    public ResultUtil<Boolean> adminRegister(@RequestBody AdminRegisterVo registerVo) {
        return userService.adminRegister(registerVo);
    }

    @PutMapping("/user")
    public ResultUtil<Boolean> updateUser(@RequestBody UpdateUserVo updateUserVo) {
        return userService.updateUser(updateUserVo);
    }

    @PutMapping("/user/psw")
    public ResultUtil<Boolean> updatePassword(@RequestBody UpdatePswVo updatePswVo) {
        return userService.updatePassword(updatePswVo);
    }

    @PostMapping("/user/{id}")
    public ResultUtil<Boolean> updateAvatarById(@RequestParam("avatar") MultipartFile file,
                                                @PathVariable("id") Integer id) {
        return userService.updateAvatarById(file, id);
    }

    @PutMapping("/user/{email}/{password}")
    public ResultUtil<Boolean> updatePasswordByEmail(@PathVariable("email") String email,
                                                     @PathVariable("password") String password) {
        return userService.updatePasswordByEmail(email, password);
    }
}
