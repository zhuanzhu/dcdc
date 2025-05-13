package com.egeo.components.order.vo.push;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class PushChildOrderExtVO implements Serializable {

    /**
     * 子订单号
     **/
    private String childCode;

    /**
     * 订单状态
     **/
    private Integer orderStatus;

    /**
     * 发货时间
     **/
    private String deliveryTime;

    /**
     * 快递公司
     **/
    private String logisticsCompany;

    /**
     * 物流单号
     **/
    private String courierNumber;

    /**
     * 运费金额
     **/
    private String deliveryFeeAmount;

    /**
     * 子订单备注
     **/
    private String remark;

    /**
     * 推送的商品信息
     **/
    private List<PushOrderProductExtVO> product;

    public String getChildCode() {
        return childCode;
    }

    public void setChildCode(String childCode) {
        this.childCode = childCode;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public String getDeliveryFeeAmount() {
        return deliveryFeeAmount;
    }

    public void setDeliveryFeeAmount(String deliveryFeeAmount) {
        this.deliveryFeeAmount = deliveryFeeAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PushOrderProductExtVO> getProduct() {
        return product;
    }

    public void setProduct(List<PushOrderProductExtVO> product) {
        this.product = product;
    }
}
