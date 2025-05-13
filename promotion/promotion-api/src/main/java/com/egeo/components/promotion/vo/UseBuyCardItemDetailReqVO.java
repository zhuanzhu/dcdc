package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class UseBuyCardItemDetailReqVO implements Serializable {

    private Long itemId;

    private Long soId;

    private Long childId;

    private Long cardId;

    private BigDecimal useAmount;

    private Long userId;

    /**
     * 订单编号
     */
    private String orderCode;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
