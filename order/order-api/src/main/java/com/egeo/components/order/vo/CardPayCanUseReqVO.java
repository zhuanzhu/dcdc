package com.egeo.components.order.vo;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/27 1:27
 * @Version V1.0
 **/
public class CardPayCanUseReqVO implements Serializable {

    private String orderCode;

    private Long orderId;

    private Integer payType;

    private Long userId;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
