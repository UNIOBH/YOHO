package com.uniobh.yoho.service;

import com.uniobh.yoho.enums.UserSexEnum;
import com.uniobh.yoho.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
* @author 23249
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-10-03 21:13:34
*/
public interface UserService extends IService<User> {
    /**
     * 登录
     * @param loginVo
     * @return
     */
    ResultUtil<Map<String, Object>> login(LoginVo loginVo);

    /**
     * 注册
     * @param registerVo
     * @return
     */
    ResultUtil<User> register(RegisterVo registerVo);

    ResultUtil<Boolean> adminRegister(AdminRegisterVo registerVo);

    /**
     * 验证邮箱是否存在
     * @param email 邮箱地址
     * @return false：邮箱已被注册，true：邮箱未被注册
     */
    ResultUtil<Boolean> isEmailExist(String email);

    ResultUtil<Boolean> isUsernameExist(String username);

    ResultUtil<List<Integer>> getUIdsBySId(Integer sId);

    ResultUtil<User> getUserById(Integer id);

    /** lbf
     * 根据用户id逻辑删除(注销)
     * @param uId
     * @return
     */
    ResultUtil<Boolean> deleteUserByUId(Integer uId);


    /** lbf
     * 根据用户id，学校id，性别，关键字，一页多少条数据分页查询
     * @param current 当前页数
     * @param size 当前页有多少条数据
     * @param uId 用户id
     * @param sId 学校id
     * @param sexEnum 性别
     * @param keyWord 关键字
     * @return
     */
    ResultUtil<Map<String, Object>> selectUsersByPage(Integer current, Integer size, Integer uId, Integer sId, UserSexEnum sexEnum, String keyWord);

    ResultUtil<Boolean> updateUser(UpdateUserVo updateUserVo);

    ResultUtil<Boolean> updatePassword(UpdatePswVo updatePswVo);

    ResultUtil<Boolean> updateAvatarById(MultipartFile file, Integer id);

    ResultUtil<Boolean> updatePasswordByEmail(String email, String password);

}
