package com.gdut.jiyi.server;


import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.model.User;
import com.gdut.jiyi.vo.UserVo;

public interface UserService {

  

    /**
     * 添加用户
     */
    ResultVo<String> addUser(User user,String repassword,String code,String cookieValue);

    /**
     * 登录
     */
    ResultVo login(String password, String phone, String code, String sessionCookieVal);
    
    
 

    /**
     * 修改密码
     */
    ResultVo<String> resetPass(String password, String repassword, String phone,String token, String code, String sessionCookieVal);//登录

    /**
     * 检查是否登录
     */
    ResultVo<User> checkLogin(String sessionCookieVal);


    /**
     * 获取用戶信息
     */
    ResultVo<UserVo> getUser(String sessionCookieVal);

    /**
     * 登出
     */
    ResultVo loginOut(String sessionCookieVal);


    /**
     * 更新用户信息
     */
    ResultVo updateUserInfo(User user, String cookie);


}
