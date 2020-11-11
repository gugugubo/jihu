package com.gdut.jiyi.server.impl;


import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.common.ServiceException;
import com.gdut.jiyi.mapper.UserMapper;
import com.gdut.jiyi.mapperMore.UserMapperMore;
import com.gdut.jiyi.model.User;
import com.gdut.jiyi.server.UserService;
import com.gdut.jiyi.util.*;
import com.gdut.jiyi.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapperMore mapperMore;
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    private HashOperations<String,String ,Object> hashOperations;





    @Override
    public ResultVo<String> addUser(User user, String repassword, String code, String cookieValue) {
//        String phoneCode = RedisUtil.getPhoneCode(valueOperations, cookieValue);
//        if (!code.equals(phoneCode)){
//            return ResultUtil.errorWithMsg("手机号验证码错误");
//        }
        //数据校验和包装
        user = wrapAndCheck( user, repassword);
        Integer res = userMapper.insertSelective(user);
        if (res > 0){
            return ResultUtil.success();
        }

        JSONObject jsonObject = new JSONObject(user);
        log.error("添加农户用户异常，user: " + jsonObject);
        return ResultUtil.error();
    }

    @Override
    public ResultVo<String> login(String password, String phone, String code, String sessionCookieVal) {
//        String imgCode = RedisUtil.getImgCode(valueOperations, sessionCookieVal);
//        if (RegUtil.isEmpty(imgCode)){
//            throw new ServiceException("图片验证码失效，请重新获取");
//        }
//        if (!imgCode.equalsIgnoreCase(code)){
//            throw new ServiceException("验证码错误");
//        }

        password = EncodeUtil.encodeWithSha(password);
        User byPhoneAndPass = mapperMore.findByPhoneAndPass(phone, password);
        if (null == byPhoneAndPass){
            throw new ServiceException("用户名或者密码错误");
        }
    
//        RedisUtil.delImgCode(valueOperations,sessionCookieVal);//删除验证码
        RedisUtil.setSessionUserId(hashOperations,sessionCookieVal, String.valueOf(byPhoneAndPass.getUserId()));
        return ResultUtil.success();
    }

 
   
    /**
     * token 和 sessionCookieVal 只能有一个为空
     * @param password password
     * @param repassword repassword
     * @param token 未登录时，需要传入，任意值
     * @param phoneCode 未登录时，需要传入
     * @param sessionCookieVal  登录时，需要传入
     * @return return
     */
    @Override
    public ResultVo<String> resetPass(String password, String repassword, String token, String phone, String phoneCode, String sessionCookieVal) {

        if (!password.equals(repassword)){
            throw new ServiceException("密码和重复密码不一致");
        }
        //加密算法
        password = EncodeUtil.encodeWithSha(password);

        if (!RegUtil.isEmpty(token) && !RegUtil.isEmpty(phoneCode)){
            if (RegUtil.isEmpty(phone)){
                throw new ServiceException("手机号不能为空");
            }

//            String resetPassPhoneCode = RedisUtil.getResetPassPhoneCode(valueOperations, token);
//            if (!phoneCode.equals(resetPassPhoneCode)){
//                throw new ServiceException("手机验证码错误");
//            }

            int result = mapperMore.upDatePasswordByPhone(phone,password);

            if (result > 0){
                return ResultUtil.successWitMsg("修改密码成功");
            }
            throw new ServiceException("手机验证码错误");
        }

        String sessionUserId = RedisUtil.getSessionUserId(hashOperations, sessionCookieVal);
        if (RegUtil.isEmpty(sessionUserId)){
            throw new ServiceException("参数传递错误");
        }

        int result = mapperMore.upDatePasswordByUserId(sessionUserId,password);
        if (result > 0){
            return ResultUtil.successWitMsg("修改密码成功");
        }
        throw new ServiceException("修改密码失败");
    }

    @Override
    public ResultVo<User> checkLogin(String sessionCookieVal) {
        String sessionUserId = RedisUtil.getSessionUserId(hashOperations, sessionCookieVal);
        if (RegUtil.isEmpty(sessionUserId)){
            throw new ServiceException("未登录");
        }
        User byUserId = mapperMore.findByUserId(sessionUserId);
        return ResultUtil.successWithData(byUserId);
      
}

    @Override
    public  ResultVo<UserVo> getUser(String sessionCookieVal) {
        String sessionUserId = RedisUtil.getSessionUserId(hashOperations, sessionCookieVal);
        User byUserId = mapperMore.findByUserId(sessionUserId);
        if (null == byUserId){
            throw new ServiceException("用戶信息不存在");
        }
        return ResultUtil.successWithData(new UserVo(byUserId));
    }

    @Override
    public ResultVo<String> loginOut(String sessionCookieVal) {
        if (RegUtil.isEmpty(sessionCookieVal)){
            throw new ServiceException("还未登录");
        }
        RedisUtil.delSessionUserId(hashOperations,sessionCookieVal);
        return ResultUtil.successWitMsg("退出成功");
    }

    @Override
    public ResultVo upDateUserInfo(User user, String cookie) {
        String uerId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(uerId)){
            return ResultUtil.errorWithMsg("cookie失效");
        }
        User byUserId = mapperMore.findByUserId(uerId);
        if (!byUserId.getPhone().equals(user.getPhone())){
            User byPhone = mapperMore.findByPhone(user.getPhone());
            if (byPhone != null){
                return ResultUtil.errorWithMsg("手机号已注册其他账号，修改失败");
            }
        }
        if (!byUserId.getUserName().equals(user.getUserName())){
            Integer existUserName = mapperMore.existUserName(user.getUserName());
            if (existUserName > 0){
                return ResultUtil.errorWithMsg("用户名已被其他用户占用");
            }
        }
        int res = mapperMore.upDateUserInfoById(user, uerId);
        if (res > 0){
            return ResultUtil.successWitMsg("修改成功");
        }
        return ResultUtil.errorWithMsg("修改失败");
    }
    



    /**
     * 进行数据校验
     * @param user  user
     * @param repassword repassword
     */
    private User wrapAndCheck(User user,String repassword){

        if (!repassword.isEmpty() && !user.getPassword().equals(repassword)){
            throw new ServiceException("密码和重复密码不一致");
        }
        //手机号是否被占用
        String phone = user.getPhone();
        Integer existPhone = mapperMore.existPhone(phone);
        if (existPhone > 0){
            throw new ServiceException("手机号已绑定其他用户");
        }
        //用户名校验
        String userName = user.getUserName();
        Integer existUserName = mapperMore.existUserName(userName);
        if (existUserName > 0){
            throw new ServiceException("该用户名已使用");
        }

        String s = EncodeUtil.encodeWithSha(user.getPassword());
        user.setPassword(s);

        user.setRegistTime(new Date());

        return user;
    }
}
