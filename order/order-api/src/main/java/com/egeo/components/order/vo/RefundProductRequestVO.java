package com.egeo.components.order.vo;

import com.egeo.dto.HttpServletRequestDTO;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class RefundProductRequestVO implements Serializable {

    private String orderId;

    private String orderCode;

    private String childCode;

    private String productId;

    private String puId;

    private Integer puNum;

    private String refundType;

    private  String soRefundCodeByFubi;

    private String soRefundCodeByCash;

    private String soRefundCodeByJiDian;

    private String soRefundCodeByBuyCard;

    private HttpServletRequestDTO req;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getChildCode() {
        return childCode;
    }

    public void setChildCode(String childCode) {
        this.childCode = childCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPuId() {
        return puId;
    }

    public void setPuId(String puId) {
        this.puId = puId;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getSoRefundCodeByFubi() {
        return soRefundCodeByFubi;
    }

    public void setSoRefundCodeByFubi(String soRefundCodeByFubi) {
        this.soRefundCodeByFubi = soRefundCodeByFubi;
    }

    public String getSoRefundCodeByCash() {
        return soRefundCodeByCash;
    }

    public void setSoRefundCodeByCash(String soRefundCodeByCash) {
        this.soRefundCodeByCash = soRefundCodeByCash;
    }

    public String getSoRefundCodeByJiDian() {
        return soRefundCodeByJiDian;
    }

    public void setSoRefundCodeByJiDian(String soRefundCodeByJiDian) {
        this.soRefundCodeByJiDian = soRefundCodeByJiDian;
    }

    public HttpServletRequestDTO getReq() {
        return req;
    }

    public void setReq(HttpServletRequestDTO req) {
        this.req = req;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getPuNum() {
        return puNum;
    }

    public void setPuNum(Integer puNum) {
        this.puNum = puNum;
    }

    public String getSoRefundCodeByBuyCard() {
        return soRefundCodeByBuyCard;
    }

    public void setSoRefundCodeByBuyCard(String soRefundCodeByBuyCard) {
        this.soRefundCodeByBuyCard = soRefundCodeByBuyCard;
    }
}
