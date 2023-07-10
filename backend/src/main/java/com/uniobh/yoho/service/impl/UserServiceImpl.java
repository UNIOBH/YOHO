package com.uniobh.yoho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniobh.yoho.enums.FileTypeEnum;
import com.uniobh.yoho.enums.ResultStatusEnum;
import com.uniobh.yoho.enums.UserSexEnum;
import com.uniobh.yoho.enums.UserTypeEnum;
import com.uniobh.yoho.pojo.User;
import com.uniobh.yoho.service.SchoolService;
import com.uniobh.yoho.service.UserService;
import com.uniobh.yoho.mapper.UserMapper;
import com.uniobh.yoho.utils.ImageUtil;
import com.uniobh.yoho.utils.JwtUtil;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-10-03 21:13:34
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private SchoolService schoolService;

    /**
     * 登录
     * @param loginVo
     * @return
     */
    @Override
    public ResultUtil<Map<String, Object>> login(LoginVo loginVo) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName, loginVo.getUsername()).eq(User::getPassword, loginVo.getPassword());
        User user = this.getOne(wrapper);
        if(user != null) {   // 登录成功
            HashMap<String, Object> map = new HashMap<>();
            map.put("nickname", user.getNickname());
            if(user.getAvatar() == null || "".equals(user.getAvatar())) map.put("avatar", ImageUtil.SERVER_PATH + "/upload/avatar/common.webp");
            else map.put("avatar", ImageUtil.SERVER_PATH + user.getAvatar());
            map.put("token", JwtUtil.generateToken(user));
            map.put("type", user.getType() == UserTypeEnum.USER ? "user" : "admin");
            map.put("sId", user.getSId());
            map.put("id", user.getId());
            return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), "登录成功", map);
        }
        // 登录失败
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "用户名或密码错误！");
    }

    /**
     * 注册
     * @param registerVo
     * @return
     */
    @Override
    public ResultUtil<User> register(RegisterVo registerVo) {
        if(this.isUsernameExist(registerVo.getUsername()).getData()) return ResultUtil.success(ResultStatusEnum.EXIST.getCode(), "用户名已存在");
        User user = new User();
        user.setName(registerVo.getUsername());
        user.setNickname(registerVo.getNickname());
        user.setSex(("女").equals(registerVo.getSex()) ? UserSexEnum.FEMALE : UserSexEnum.MALE);
        user.setPassword(registerVo.getPassword());
        user.setEmail(registerVo.getEmail());
        user.setSId(registerVo.getSchool());
        user.setType(UserTypeEnum.USER);
        user.setAvatar("/upload/avatar/common.webp");
        boolean save = this.save(user);
        if(save) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(),  user);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode(),  "注册失败");
    }

    /**
     * 管理员注册
     * @param registerVo
     * @return
     */
    @Override
    public ResultUtil<Boolean> adminRegister(AdminRegisterVo registerVo) {
        User user = new User();
        user.setName(registerVo.getUsername());
        user.setPassword(registerVo.getPassword());
        user.setType(UserTypeEnum.ADMIN);
        boolean b = this.save(user);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 检查邮箱地址是否已注册
     * @param email 邮箱地址
     * @return
     */
    @Override
    public ResultUtil<Boolean> isEmailExist(String email) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        User user = this.getOne(wrapper);
        if(user != null) return ResultUtil.success(ResultStatusEnum.EXIST.getCode(), "邮箱已被注册", true);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), "邮箱未被注册",false);
    }

    /**
     * 验证用户名是否已经存在
     * @param username 需要检查的用户名
     * @return
     */
    @Override
    public ResultUtil<Boolean> isUsernameExist(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(username != null, User::getName, username);
        User user = this.getOne(queryWrapper);
        if(user == null) return ResultUtil.success(ResultStatusEnum.EXIST.getCode(), "用户名已存在", false);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), true);
    }

    /**
     * 根据学校id获取用户id
     * @param sId 学校id
     * @return 由用户id构成的集合
     */
    @Override
    public ResultUtil<List<Integer>> getUIdsBySId(Integer sId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(sId != null && sId != 0, User::getSId, sId);
        List<User> list = this.list(queryWrapper);
        if(list.size() == 0 || list == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        ArrayList<Integer> res = new ArrayList<>();
        for (User user : list) {
            res.add(user.getId());
        }
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 根据用户id获取用户
     * @param id 用户id
     * @return
     */
    @Override
    public ResultUtil<User> getUserById(Integer id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(id != 0 && id != null, User::getId, id);
        User user = this.getOne(wrapper);
        if(user == null) return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无此用户");
        user.setAvatar(ImageUtil.SERVER_PATH + user.getAvatar());
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), user);
    }

    /** lbf
     * 根据用户id逻辑删除（注销）
     * @param uId 用户id
     * @return
     */
    @Override
    public ResultUtil<Boolean> deleteUserByUId(Integer uId) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(uId != null && uId != 0, User::getId, uId);
        boolean res = this.remove(updateWrapper);
        if(res == false){
            return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
        }
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 根据用户id，学校id，性别，关键字，一页多少条数据分页查询
     * @param current 当前页数
     * @param size 当前页有多少条数据
     * @param uId 用户id
     * @param sId 学校id
     * @param sexEnum 性别枚举
     * @param keyWord 关键字
     * @return
     */
    @Override
    public ResultUtil<Map<String, Object>> selectUsersByPage(Integer current, Integer size, Integer uId, Integer sId, UserSexEnum sexEnum, String keyWord) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(uId!=null && uId != 0, User::getId, uId)
                .eq(User::getType, UserTypeEnum.USER) // 用户必须是普通用户
                .eq(sId != null && sId != 0, User::getSId, sId)
                .eq(sexEnum != null, User::getSex, sexEnum)
                .and(keyWord != null && !("".equals(keyWord)), i->i.like( User::getName, keyWord)
                        .or()
                        .like(User::getNickname, keyWord)
                );
        Page<User> pageUser = new Page<>(current, size);
        Page<User> page = this.page(pageUser, queryWrapper);
        List<User> records = page.getRecords();
        System.out.println(records);
        long pages = page.getPages();
        System.out.println("pages：" + pages);
        if(pages == 0 || records.size() == 0 || records == null){
            return ResultUtil.success(ResultStatusEnum.NO_DATA.getCode(), "暂无数据");
        }
        HashMap<String, Object> res = new HashMap<>();
        res.put("pages", pages);
        ArrayList<UserPageVo> list = new ArrayList<>();
        for(User user : records){
            UserPageVo userPageVo = new UserPageVo();
            userPageVo.setUId(user.getId());
            userPageVo.setUName(user.getName());
            userPageVo.setUNickName(user.getNickname());
            userPageVo.setUserSex(user.getSex().getSex() == UserSexEnum.FEMALE.getSex()? UserSexEnum.FEMALE .getSex()  : UserSexEnum.MALE.getSex());
            userPageVo.setEmail(user.getEmail());
            userPageVo.setRegisterTime(user.getRegisterTime());
            userPageVo.setLastTime(user.getLastTime());
            userPageVo.setSName(schoolService.getSchoolById(user.getSId()).getData().getName());
            userPageVo.setUDel(user.getDel());
            list.add(userPageVo);
        }
        res.put("list", list);
        return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), res);
    }

    /**
     * 修改用户消息
     * @param updateUserVo
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateUser(UpdateUserVo updateUserVo) {
        Boolean isUsernameExist = true;
        if(updateUserVo.getUsername() != null) isUsernameExist = isUsernameExist(updateUserVo.getUsername()).getData();
        if(!isUsernameExist) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "用户名已存在");
        Boolean isEmailExist = false;
        if(isEmailExist) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "邮箱已存在");
        User user = new User();
        user.setId(updateUserVo.getId());
        if(updateUserVo.getUsername() != null) user.setName(updateUserVo.getUsername());
        if(updateUserVo.getNickname() != null) user.setNickname(updateUserVo.getNickname());
        if(updateUserVo.getSex() != null) user.setSex(updateUserVo.getSex() == UserSexEnum.FEMALE.getSex() ? UserSexEnum.FEMALE : UserSexEnum.MALE);
        boolean b = this.updateById(user);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 根据旧密码修改密码
     * @param updatePswVo
     * @return
     */
    @Override
    public ResultUtil<Boolean> updatePassword(UpdatePswVo updatePswVo) {
        User user = this.getById(updatePswVo.getId());
        if(!user.getPassword().equals(updatePswVo.getOldPassword())) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "旧密码错误");
        user.setPassword(updatePswVo.getNewPassword());
        boolean b = this.updateById(user);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 修改头像
     * @param file
     * @param id
     * @return
     */
    @Override
    public ResultUtil<Boolean> updateAvatarById(MultipartFile file, Integer id) {
        MultipartFile[] imgs = {file};
        List<String> upload = ImageUtil.upload(imgs, FileTypeEnum.AVATAR, id);
        if(upload.size() == 0) return ResultUtil.error(ResultStatusEnum.FAIL.getCode(), "上传失败");
        User user = new User();
        user.setId(id);
        user.setAvatar(upload.get(0));
        boolean b = this.updateById(user);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode());
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }

    /**
     * 根据邮箱修改密码
     * @param email
     * @return
     */
    @Override
    public ResultUtil<Boolean> updatePasswordByEmail(String email, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        User user = new User();
        user.setPassword(password);
        boolean b = this.update(user, wrapper);
        if(b) return ResultUtil.success(ResultStatusEnum.PERFECT.getCode(), b);
        return ResultUtil.error(ResultStatusEnum.FAIL.getCode());
    }
}




