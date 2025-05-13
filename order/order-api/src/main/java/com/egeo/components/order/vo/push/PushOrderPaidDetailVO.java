package com.egeo.components.order.vo.push;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class PushOrderPaidDetailVO implements Serializable {

    /**
     * 1、饭卡支付金额 2、积点支付金额 3、微信支付金额
     **/
    private String type;

    /**
     * 支付金额
     **/
    private String paidAmount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }
}
