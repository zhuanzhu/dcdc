package com.egeo.components.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class RefundItemVo implements Serializable {
    private String skuId;
    private String skuName;
    private Integer refundNum;
    private BigDecimal price;
    private BigDecimal refundAmount;
    private BigDecimal refundDeliveryFee;
    private Long soItemId;
    private Integer source;
    private Long platformId;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getRefundDeliveryFee() {
        return refundDeliveryFee;
    }

    public void setRefundDeliveryFee(BigDecimal refundDeliveryFee) {
        this.refundDeliveryFee = refundDeliveryFee;
    }

    public Long getSoItemId() {
        return soItemId;
    }

    public void setSoItemId(Long soItemId) {
        this.soItemId = soItemId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
}
