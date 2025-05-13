package com.egeo.components.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/27 1:36
 * @Version V1.0
 **/
public class UserCardDetailVO implements Serializable {

    private Long cardId;

    private Integer cardType;

    private String cardName;

    private BigDecimal cardCash;

    /**
     * '有效期起始日期'
     **/
    private Date expireDateStart;

    /**
     * '有效期结束日期'
     **/
    private Date expireDateEnd;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public BigDecimal getCardCash() {
        return cardCash;
    }

    public void setCardCash(BigDecimal cardCash) {
        this.cardCash = cardCash;
    }

    public Date getExpireDateStart() {
        return expireDateStart;
    }

    public void setExpireDateStart(Date expireDateStart) {
        this.expireDateStart = expireDateStart;
    }

    public Date getExpireDateEnd() {
        return expireDateEnd;
    }

    public void setExpireDateEnd(Date expireDateEnd) {
        this.expireDateEnd = expireDateEnd;
    }
}
