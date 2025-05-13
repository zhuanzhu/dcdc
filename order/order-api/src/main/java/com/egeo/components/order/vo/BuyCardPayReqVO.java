package com.egeo.components.order.vo;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/27 14:42
 * @Version V1.0
 **/
public class BuyCardPayReqVO implements Serializable {

    private Long orderId;

    private String cardIds;

    private String orderCode;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCardIds() {
        return cardIds;
    }

    public void setCardIds(String cardIds) {
        this.cardIds = cardIds;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
