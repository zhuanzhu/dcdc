package com.egeo.components.order.dto;

import java.util.Date;

public class QmOrderDTO {
    private Long id;
    private Long soId;
    private String outTradeNo;
    private String returnUrl;
    private String notifyUrl;
    private Date orderTime;
    private Date expireTime;
    private Date createTime;
    private String remark;
    private String orderCode;
    private Integer syncPayStatus;
    private Integer syncPayCount;
    private Date orderPayTime;
    private Date syncTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getSyncPayStatus() {
        return syncPayStatus;
    }

    public void setSyncPayStatus(Integer syncPayStatus) {
        this.syncPayStatus = syncPayStatus;
    }

    public Integer getSyncPayCount() {
        return syncPayCount;
    }

    public void setSyncPayCount(Integer syncPayCount) {
        this.syncPayCount = syncPayCount;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public static class OrderState{
        public static final String CANCEL="0";
        public static final String SHIP="30";
        public static final String FINISH="40";
    }

    public static class SyncPayStatus{
        public static final Integer INIT=0;
        public static final Integer WAIT_SYNC=1;
        public static final Integer SYNCED=2;
    }
}
