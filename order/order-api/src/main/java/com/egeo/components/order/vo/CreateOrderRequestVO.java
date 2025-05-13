package com.egeo.components.order.vo;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class CreateOrderRequestVO implements Serializable {

    private Long exchangeId;

    private Long exchangeCouponUnitId;

    private Long exchangeCouponBatchId;

    /**
     * orderType为8时,表示以旧换新补差价
     **/
    private Integer orderType;

    /**
     * 仓库id
     **/
    private Long storeId;

    /**
     * 下单快递地址ID
     **/
    private Long receiveAddressId;

    /**
     * 订单类型 0:直接下单 1:购物车
     **/
    private Integer type;

    /**
     * 购物车ids
     **/
    private String cartItemIds;


    private Long puId;

    /**
     * 购买数量
     **/
    private Integer num;

    /**
     * 是否用fubi支付
     **/
    private Integer useFubi;

    /**
     * 示例：{"1":"自营商品用户备注","2":"三方商品用户备注"}
     **/
    private String remark;
    private Long invoiceId;

    /**
     * 手机号
     **/
    private String phone;


    private Integer couponType;


    private Long couponUnitId;


    private String deliveryPrice;

    /**
     * 渠道商品ID
     **/
    private String channelProductId;

    /**
     * 来源
     **/
    private Integer source;

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Long getExchangeCouponUnitId() {
        return exchangeCouponUnitId;
    }

    public void setExchangeCouponUnitId(Long exchangeCouponUnitId) {
        this.exchangeCouponUnitId = exchangeCouponUnitId;
    }

    public Long getExchangeCouponBatchId() {
        return exchangeCouponBatchId;
    }

    public void setExchangeCouponBatchId(Long exchangeCouponBatchId) {
        this.exchangeCouponBatchId = exchangeCouponBatchId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(Long receiveAddressId) {
        this.receiveAddressId = receiveAddressId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCartItemIds() {
        return cartItemIds;
    }

    public void setCartItemIds(String cartItemIds) {
        this.cartItemIds = cartItemIds;
    }

    public Long getPuId() {
        return puId;
    }

    public void setPuId(Long puId) {
        this.puId = puId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getUseFubi() {
        return useFubi;
    }

    public void setUseFubi(Integer useFubi) {
        this.useFubi = useFubi;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Long getCouponUnitId() {
        return couponUnitId;
    }

    public void setCouponUnitId(Long couponUnitId) {
        this.couponUnitId = couponUnitId;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getChannelProductId() {
        return channelProductId;
    }

    public void setChannelProductId(String channelProductId) {
        this.channelProductId = channelProductId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
