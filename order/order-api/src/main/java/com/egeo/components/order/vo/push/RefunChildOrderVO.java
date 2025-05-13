package com.egeo.components.order.vo.push;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class RefunChildOrderVO implements Serializable {
    /**
     * 子订单编号
     **/
    private String childCode;
    /**
     *订单状态
     **/
    private Integer orderStatus;
    /**
     *退款金额
     **/
    private String refundAmount;
    /**
     *退款运费
     **/
    private String refundDeliveryFeeAmount;
    /**
     *备注
     **/
    private String remark;

    /**
     *  产品信息
     **/
    private List<RefundProductVO> product;

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

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundDeliveryFeeAmount() {
        return refundDeliveryFeeAmount;
    }

    public void setRefundDeliveryFeeAmount(String refundDeliveryFeeAmount) {
        this.refundDeliveryFeeAmount = refundDeliveryFeeAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RefundProductVO> getProduct() {
        return product;
    }

    public void setProduct(List<RefundProductVO> product) {
        this.product = product;
    }
}
