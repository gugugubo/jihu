package com.gdut.jiyi.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Card implements Serializable {
    @ApiModelProperty(value = "卡片id")
    private Integer cardId;

    @ApiModelProperty(value = "卡包id")
    private Integer cardBagId;

    @ApiModelProperty(value = "问题-富文本格式")
    private String question;

    @ApiModelProperty(value = "答案-富文本格式")
    private String answer;

    @ApiModelProperty(value = "更多解释-富文本格式")
    private String more;

    private static final long serialVersionUID = 1L;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCardBagId() {
        return cardBagId;
    }

    public void setCardBagId(Integer cardBagId) {
        this.cardBagId = cardBagId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cardId=").append(cardId);
        sb.append(", cardBagId=").append(cardBagId);
        sb.append(", question=").append(question);
        sb.append(", answer=").append(answer);
        sb.append(", more=").append(more);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}