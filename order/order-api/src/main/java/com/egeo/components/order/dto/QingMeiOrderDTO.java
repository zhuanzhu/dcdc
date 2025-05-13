package com.egeo.components.order.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 清美向收银台统一下单
 */
public class QingMeiOrderDTO implements Serializable {
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
     * 下单时间，格式（yyyy-MM-dd HH:mm:ss）
     */
    @NotBlank(message = "orderTime不可为空")
    private String orderTime;

    /**
     * 下单员工唯一标识
     */
    @NotBlank(message = "openId不可为空")
    private String openId;

    /**
     * 买家备注
     */
    private String reason;

    /**
     * 时间戳
     */
    @NotBlank(message = "timestamp不可为空")
    private String timestamp;

    /**
     * 订单总金额（含运费），支付的总金额
     */
    @NotNull(message = "totalAmount不可为空")
    private BigDecimal totalAmount;

    /**
     * 附加信息,原样返回,复杂参数建议urlencode
     */
    private String attach;

    /**
     * 支付完成后，跳转的商家订单显示URL
     */
    @NotBlank(message = "returnUrl不可为空")
    private String returnUrl;

    /**
     * 商家提供的notifyUrl。订单完成后，通知商家订单成功
     */
    @NotBlank(message = "notifyUrl不可为空")
    private String notifyUrl;

    /**
     * 订单支付超时时间,格式（yyyy-MM-dd HH:mm:ss）
     */
    @NotBlank(message = "expireTime不可为空")
    private String expireTime;

    /**
     *订单信息
     */
    @Valid
    @NotEmpty(message = "tradeList不可为空")
    private List<QingMeiChildOrderDTO> tradeList;

    /**
     * 签名
     */
    @NotBlank(message = "sign不可为空")
    private String sign;

    private BigDecimal totalShippingFee;

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

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
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

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public List<QingMeiChildOrderDTO> getTradeList() {
        return tradeList;
    }

    public void setTradeList(List<QingMeiChildOrderDTO> tradeList) {
        this.tradeList = tradeList;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public BigDecimal getTotalShippingFee() {
        return totalShippingFee;
    }

    public void setTotalShippingFee(BigDecimal totalShippingFee) {
        this.totalShippingFee = totalShippingFee;
    }

    public void mergeChildItems(){
        for (QingMeiChildOrderDTO child:this.tradeList){
            child.setGoodsList(child.mergeItems());
        }
    }

}
