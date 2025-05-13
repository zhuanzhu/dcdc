package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldOrderItemsResponseDTO implements Serializable {

    private String orderSn;

    private String orderNo;

    private Integer orderStatus;

    private Boolean isPay;

    private String goodsMoney;

    private String deliverMoney;

    private String totalTemplateDelivery;

    private String discountMoney;

    private String totalMoney;

    private String invoiceMoney;

    private String realTotalMoney;

    private List<WorldBuyGoodsItemsResponseDTO> goods_items;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getPay() {
        return isPay;
    }

    public void setPay(Boolean pay) {
        isPay = pay;
    }

    public String getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(String goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public String getDeliverMoney() {
        return deliverMoney;
    }

    public void setDeliverMoney(String deliverMoney) {
        this.deliverMoney = deliverMoney;
    }

    public String getTotalTemplateDelivery() {
        return totalTemplateDelivery;
    }

    public void setTotalTemplateDelivery(String totalTemplateDelivery) {
        this.totalTemplateDelivery = totalTemplateDelivery;
    }

    public String getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(String discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(String invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public String getRealTotalMoney() {
        return realTotalMoney;
    }

    public void setRealTotalMoney(String realTotalMoney) {
        this.realTotalMoney = realTotalMoney;
    }

    public List<WorldBuyGoodsItemsResponseDTO> getGoods_items() {
        return goods_items;
    }

    public void setGoods_items(List<WorldBuyGoodsItemsResponseDTO> goods_items) {
        this.goods_items = goods_items;
    }
}
