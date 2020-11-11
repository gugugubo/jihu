package com.gdut.jiyi.controller;


import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.model.User;
import com.gdut.jiyi.server.UserService;
import com.gdut.jiyi.util.CookieUtil;
import com.gdut.jiyi.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResultVo<String> addUser(@Validated @RequestBody User user,
                                    @NotBlank(message = "重复密码不能为空") @RequestParam("repassword") String repassword,
                                    @NotBlank(message = "验证码不能为空")
                                @Size(min = 4,max = 4,message = "验证码长度只能为4") @RequestParam("code") String code,
                                    HttpServletRequest request, HttpServletResponse response
    ){
        String sessionCookieVal = CookieUtil.getSessionCookieVal(request, response);
        return userService.addUser(user,repassword,code,sessionCookieVal);
    }

 

    @PostMapping("/login")
    public ResultVo<String> login(@NotBlank(message = "密码不能为空") @RequestParam("password") String password,
                                  @NotBlank(message = "手机号不能为空")
                          @Pattern(regexp="^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$",
                                  message = "手机号格式错误")
                          @RequestParam("phone") String phone,
                                  @NotBlank(message = "验证码不能为空")
                          @Size(min = 4,max = 4,message = "验证码长度只能为4")
                              @RequestParam("code") String code, HttpServletRequest request,
                                 HttpServletResponse response){
        String sessionCookieVal = CookieUtil.getSessionCookieVal(request, response);
        return userService.login(password,phone,code,sessionCookieVal);
    }

    /**
     * 未登录通过手机号码重置密码
     */
    @PostMapping("/resetPassByPhone")
    @ApiOperation("")
    public ResultVo<String> resetPassByPhone(
            @NotBlank(message = "密码不能为空") @RequestParam("password") String password,
            @NotBlank(message = "重复密码不能为空") @RequestParam("repassword") String repassword,
            @NotBlank(message = "手机号不能为空")
            @Pattern(regexp="^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$",
                           message = "手机号格式错误")  @RequestParam("phone") String phone,
            @NotBlank(message = "验证码不能为空")
            @Size(min = 4,max = 4,message = "验证码长度只能为4")
            @RequestParam("code")          String code){
            return userService.resetPass(password,repassword,"byPhone",phone,code,null);
    }

    /**
     * 已经登录得用户可以直接重置密码
     */
    @PostMapping("/resetPassByUserId")
    public ResultVo resetPassByUserId(
            @NotBlank(message = "密码不能为空") @RequestParam("password")  String password,
            @NotBlank(message = "重复密码不能为空")  @RequestParam("repassword") String repassword,
            HttpServletRequest request, HttpServletResponse response){
        String sessionCookieVal = CookieUtil.getSessionCookieVal(request, response);
        return userService.resetPass(password,repassword,null,null,null,sessionCookieVal);
    }

    

    /**
     * 检查是否登录
     */
    @GetMapping("/checkLogin")
    public ResultVo<User> checkLogin(
            HttpServletRequest request, HttpServletResponse response){
        String sessionCookieVal = CookieUtil.getSessionCookieVal(request, response);
        return userService.checkLogin(sessionCookieVal);
    }

    /**
     * 得到本用户得信息
     * @param request request
     * @param response response
     */
    @GetMapping("/getUserInfo")
    @ApiOperation("")
    public ResultVo<UserVo> getUserInfo(HttpServletRequest request, HttpServletResponse response){
        String sessionCookieVal = CookieUtil.getSessionCookieVal(request, response);
        return  userService.getUser(sessionCookieVal);
    }


    /**
     * 退出登录
     * @param cookie cookie
     * @return String
     */
    @GetMapping("/loginOut")
    public ResultVo<String> loginOut(@CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie){
        return  userService.loginOut(cookie);
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/updateUserInfo")
    public ResultVo updateUserInfo(@RequestBody User user, @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie){
        return  userService.updateUserInfo(user,cookie);
    }


}
