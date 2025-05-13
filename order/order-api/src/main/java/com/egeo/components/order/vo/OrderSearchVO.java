package com.egeo.components.order.vo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderSearchVO {

    private Long companyId;
    private Long storeId;
    private String keyWords;
    private String orderCode;
    private String email;
    private String puName;
    private Long startTime;
    private  Long endTime;
    private Integer status;
    private Integer orderConfirmStatus;
    private Integer orderPayStatus;
    private Integer paymentType;
    private Boolean showTest;
    private Integer auditStatus;
    private Integer pageNo;
    private Integer pageSize;
    private Integer confirmStatus;
    private Integer payStatus;
    private Long platformId;
    private boolean refundFlag;
    private List<Long> soIds;
    private Map<Long,String> companyNameMap;
    /**
     * 收货人手机
     */
    private String goodReceiverMobile;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuName() {
        return puName;
    }

    public void setPuName(String puName) {
        this.puName = puName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderConfirmStatus() {
        return orderConfirmStatus;
    }

    public void setOrderConfirmStatus(Integer orderConfirmStatus) {
        this.orderConfirmStatus = orderConfirmStatus;
    }

    public Integer getOrderPayStatus() {
        return orderPayStatus;
    }

    public void setOrderPayStatus(Integer orderPayStatus) {
        this.orderPayStatus = orderPayStatus;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Boolean getShowTest() {
        return showTest;
    }

    public void setShowTest(Boolean showTest) {
        this.showTest = showTest;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getPageNo() {
        return Objects.isNull(pageNo)?1:pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return Objects.isNull(pageSize)?20:pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public boolean isRefundFlag() {
        return refundFlag;
    }

    public void setRefundFlag(boolean refundFlag) {
        this.refundFlag = refundFlag;
    }

    public Map<Long, String> getCompanyNameMap() {
        return companyNameMap;
    }

    public void setCompanyNameMap(Map<Long, String> companyNameMap) {
        this.companyNameMap = companyNameMap;
    }

    public List<Long> getSoIds() {
        return soIds;
    }

    public void setSoIds(List<Long> soIds) {
        this.soIds = soIds;
    }

    public String getGoodReceiverMobile() {
        return goodReceiverMobile;
    }

    public void setGoodReceiverMobile(String goodReceiverMobile) {
        this.goodReceiverMobile = goodReceiverMobile;
    }
}
