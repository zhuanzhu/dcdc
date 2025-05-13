package com.egeo.components.promotion.vo;

import java.io.Serializable;

/**
 * @Description 发放卡VO
 * @Author lsl
 * @Version V1.0
 **/
public class GrantUserBuyCardVO implements Serializable {

    private Long userId;

    private Integer cardNum;

    private Long cardId;



    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
