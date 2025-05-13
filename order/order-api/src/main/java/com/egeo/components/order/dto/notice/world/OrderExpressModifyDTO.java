package com.egeo.components.order.dto.notice.world;

import java.io.Serializable;

/**
 * @Description 订单物流发货更新接口（非必要可以不用）
 * @Author lsl
 * @Version V1.0
 **/
public class OrderExpressModifyDTO implements Serializable {

    /**
     * 		String	是	渠道商订单号
     */
    private String orderSn;
    /**
     * 	String	是	创建订单返回的orderNo
     */
    private String orderNo;
    /**
     * 		String	是	物流公司编码
     */
    private String expressCode;
    /**
     * 		String	是	物流公司名称
     */
    private String expressName;
    /**
     * 	String	是	物流公司单号
     */
    private String expressNo;

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

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }
}
