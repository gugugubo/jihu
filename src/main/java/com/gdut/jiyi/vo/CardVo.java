package com.gdut.jiyi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 古春波
 * @description CardVo
 * @Date 2020/11/11 15:43
 * @version 1.0
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardVo {

    @ApiModelProperty(value = "卡包id")
    @NotNull(message = "cardBagId不能为null")
    private Integer cardBagId;
    
    @NotBlank(message = "question不能为空")
    @ApiModelProperty(value = "问题-富文本格式")
    private String question;

    @NotBlank(message = "answer不能为空")
    @ApiModelProperty(value = "答案-富文本格式")
    private String answer;

    @ApiModelProperty(value = "更多解释-富文本格式")
    private String more;
}
