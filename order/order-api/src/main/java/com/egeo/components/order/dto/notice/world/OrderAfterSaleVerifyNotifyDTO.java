package com.egeo.components.order.dto.notice.world;

import java.io.Serializable;

/**
 * @Description 全球购订单售后审核通知回传接口
 * @Author lsl
 * @Version V1.0
 **/
public class OrderAfterSaleVerifyNotifyDTO implements Serializable {
    /**
     * 	String	是	渠道商订单号
     */
    private String orderSn;
    /**
     * 	String	是	子单号
     */
    private String orderNo;
    /**
     * 	String	是	审核状态（1-审核通过，2-审核拒绝）
     */
    private String afterVerifyStatus;
    /**
     * 	String	是	审核结果
     */
    private String afterVerifyRemark;
    /**
     * 	String	是	审核时间
     */
    private String afterVerifyTime;

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

    public String getAfterVerifyStatus() {
        return afterVerifyStatus;
    }

    public void setAfterVerifyStatus(String afterVerifyStatus) {
        this.afterVerifyStatus = afterVerifyStatus;
    }

    public String getAfterVerifyRemark() {
        return afterVerifyRemark;
    }

    public void setAfterVerifyRemark(String afterVerifyRemark) {
        this.afterVerifyRemark = afterVerifyRemark;
    }

    public String getAfterVerifyTime() {
        return afterVerifyTime;
    }

    public void setAfterVerifyTime(String afterVerifyTime) {
        this.afterVerifyTime = afterVerifyTime;
    }
}
