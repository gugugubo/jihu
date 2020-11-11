package com.gdut.jiyi.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author 古春波
 * @Description 用户简单信息
 * @Date 2020/9/29 17:06
 * @Version 1.0
 **/
public class SimpleUserVo {


    @ApiModelProperty(value = "头像")
    private String avatar;

    

    @ApiModelProperty(value = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "角色，0表示普通用户，1表示农户")
    private Integer role;

    
}
