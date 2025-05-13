package com.egeo.components.order.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class QmOrderRefundDTO implements Serializable {
    /**
     * appId
     */
    @NotBlank(message = "appId不可为空")
    private String appId;

    /**
     * 外部交易单号
     */
    @NotBlank(message = "outTradeNo不可为空")
    private String outTradeNo;

    /**
     *收银台订单号
     */
    @NotBlank(message = "tradeNo不可为空")
    private String tradeNo;

    /**
     * 子订单号
     */
    @NotBlank(message = "orderNo不可为空")
    private String orderNo;

    /**
     * 退款单号
     */
    @NotBlank(message = "refundNo不可为空")
    private String refundNo;

    /**
     * 时间戳
     */
    @NotBlank(message = "timestamp不可为空")
    private String timestamp;

    /**
     * 退款金额(退款总金额)
     */
    @NotNull(message = "refundAmount不可为空")
    private BigDecimal refundAmount;

    /**
     * 退运费金额
     */
    private BigDecimal refundFee;

    /**
     * 签名
     */
    @NotBlank(message = "sign不可为空")
    private String sign;

    /**
     * 退款商品信息
     */
    @Valid
    @NotEmpty(message = "goodsList不可为空")
    private List<QmRefundItemDTO> goodsList;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<QmRefundItemDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<QmRefundItemDTO> goodsList) {
        this.goodsList = goodsList;
    }

}
