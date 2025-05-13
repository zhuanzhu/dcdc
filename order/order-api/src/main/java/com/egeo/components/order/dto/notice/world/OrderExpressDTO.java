package com.egeo.components.order.dto.notice.world;

import java.io.Serializable;

/**
 * @Description 订单物流发货通知接口
 * @Author lsl
 * @Version V1.0
 **/
public class OrderExpressDTO implements Serializable {
    /**
     * 		String	是	系统内部主单号
     */
    private String orderNo;
    /**
     * 	String	是	系统内部单号(创建订单返回的orderNo)
     */
    private String hnOrderNo;
    /**
     * 	String	是	系统对上游供应商出口子订单号
     */
    private String dOrderNo;
    /**
     * String	是	渠道商订单号（海关申报单号）
     */
    private String order_sn	;
    /**
     * 		String	是	物流公司编码
     */
    private String expressCode;
    /**
     * 		String	是	物流公司名称
     */
    private String expressName;
    /**
     * 		String	是	物流单号
     */
    private String expressNo;
    /**
     * 		int	是	发货时间，时间戳
     */
    private String deliveryTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getHnOrderNo() {
        return hnOrderNo;
    }

    public void setHnOrderNo(String hnOrderNo) {
        this.hnOrderNo = hnOrderNo;
    }

    public String getdOrderNo() {
        return dOrderNo;
    }

    public void setdOrderNo(String dOrderNo) {
        this.dOrderNo = dOrderNo;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
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

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
