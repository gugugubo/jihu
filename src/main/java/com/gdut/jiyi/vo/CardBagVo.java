package com.gdut.jiyi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author 古春波
 * @description CardBagVo
 * @Date 2020/11/11 15:36
 * @version 1.0
 **/
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CardBagVo {

    
    @NotBlank(message = "name不能为空")
    @ApiModelProperty(value = "卡包的名字")
    private String name;

    @NotBlank(message = "info不能为空")
    @ApiModelProperty(value = "卡包的一些描述信息")
    private String info;

    
    @ApiModelProperty(value = "是否公开，0为私有，1为公开")
    private Boolean ifPrivate;

    @ApiModelProperty(value = "卡包照片")
    @NotBlank(message = "pic不能为空")
    private String pic;
    
}
