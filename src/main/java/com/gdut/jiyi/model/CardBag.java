package com.gdut.jiyi.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CardBag implements Serializable {
    @ApiModelProperty(value = "卡包id")
    private Integer cardBagId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "卡包的名字")
    private String name;

    @ApiModelProperty(value = "卡包的一些描述信息")
    private String info;

    @ApiModelProperty(value = "是否公开，0为私有，1为公开")
    private Boolean ifPrivate;

    @ApiModelProperty(value = "卡包照片")
    private String pic;

    private static final long serialVersionUID = 1L;

    public Integer getCardBagId() {
        return cardBagId;
    }

    public void setCardBagId(Integer cardBagId) {
        this.cardBagId = cardBagId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getIfPrivate() {
        return ifPrivate;
    }

    public void setIfPrivate(Boolean ifPrivate) {
        this.ifPrivate = ifPrivate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cardBagId=").append(cardBagId);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", info=").append(info);
        sb.append(", ifPrivate=").append(ifPrivate);
        sb.append(", pic=").append(pic);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}