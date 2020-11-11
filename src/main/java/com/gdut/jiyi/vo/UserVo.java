package com.gdut.jiyi.vo;


import com.gdut.jiyi.model.User;

public class UserVo extends User {
    private String avatarUrl;

    public UserVo(User user){
        super.setUserId(user.getUserId());
        super.setUserName(user.getUserName());
        super.setPassword(user.getPassword());
        super.setPhone(user.getPhone());
        super.setAvatar(user.getAvatar());
        super.setRegistTime(user.getRegistTime());
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
