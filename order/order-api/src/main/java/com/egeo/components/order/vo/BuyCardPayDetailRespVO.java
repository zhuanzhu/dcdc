package com.egeo.components.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/27 14:42
 * @Version V1.0
 **/
public class BuyCardPayDetailRespVO implements Serializable {

    private Long cardId;

    private BigDecimal payAmount;

    private String cardName;


    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
