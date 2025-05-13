package com.egeo.components.order.vo.push;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class OrderRefundDetailVO implements Serializable {

    /**
     * 退款账户类型:1、饭卡支付金额 2、积点支付金额 3、微信支付金额
     **/
    private String type;


    /**
     * 退款账户对应的退款金额
     **/
    private String refundAmount;

    public OrderRefundDetailVO() {

    }

    public OrderRefundDetailVO(String type, String refundAmount) {
        this.type = type;
        this.refundAmount = refundAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }
}
