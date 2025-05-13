package com.egeo.components.order.vo;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/25 13:58
 * @Version V1.0
 **/
public class OrderByUserAndStatusRequestVO implements Serializable {

    private Long userId;
    private Integer orderStatus;
    private Long platformId;
    private Long f;
    private Long clientId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getF() {
        return f;
    }

    public void setF(Long f) {
        this.f = f;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
