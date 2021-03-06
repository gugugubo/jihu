package com.gdut.jiyi.mapperMore;

import com.gdut.jiyi.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 古春波
 * @description UserMapperMore
 * @Date 2020/11/10 18:03
 * @version 1.0
 **/
public interface UserMapperMore {

    /**
     * 电话号码是否已存在
     * @param phone phone
     * @return Integer
     */
    Integer existPhone(String phone);


    /**
     * 用户名是否已存在
     * @param userName userName
     * @return Integer
     */
    Integer existUserName(String userName);

    /**
     * 根据用户名和密码查找用户
     * @param phone phone 
     * @param pass pass
     * @return
     */
    User findByPhoneAndPass(@Param("phone") String phone, @Param("pass") String pass);

    User findByUserId(String userId);

    int upDatePasswordByPhone(@Param("phone") String phone, @Param("password") String password);


    int upDatePasswordByUserId(@Param("userId") String userId,@Param("password")  String password);

    String getPassByUserId(String uerId);

    int resetPassById(@Param("password")  String password, @Param("uerId")  String uerId);

    User findByPhone(String phone);

    int upDateUserInfoById(@Param("user") User user, @Param("uid") String uerId);
}
