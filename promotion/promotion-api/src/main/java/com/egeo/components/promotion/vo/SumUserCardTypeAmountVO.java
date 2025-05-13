package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class SumUserCardTypeAmountVO implements Serializable {
    private Integer cardType;

    private String typeName;

    private BigDecimal cardCash;

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getCardCash() {
        return cardCash;
    }

    public void setCardCash(BigDecimal cardCash) {
        this.cardCash = cardCash;
    }
}
