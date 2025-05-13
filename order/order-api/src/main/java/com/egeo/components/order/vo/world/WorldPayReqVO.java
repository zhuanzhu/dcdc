package com.egeo.components.order.vo.world;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldPayReqVO implements Serializable {

    /**
     * 	String	是	渠道商订单号（必须是支付公司推送海关申报的时候填写的订单号）
     **/
    private String orderSn;

    /**
     *	String	是	支付时间，标准时间戳，精确到秒
     **/
    private String payTime;

    /**
     *	String	是	支付公司类型(不支持，则默认写0即可)
     **/
    private String payWay;
    /**
     *	String	是	电商平台海关备案编码（保税和直邮必填），一般贸易为“电商平台资质编码”
     **/
    private String enterpriseNum;
    /**
     *	String	是	电商平台海关备案名称（保税和直邮必填），一般贸易为“电商平台资质”
     **/
    private String enterpriseName;
    /**
     *	String	是	支付公司海关备案名称（保税和直邮必填），一般贸易为“支付公司名称或者余额支付或者其他支付”
     **/
    private String payCommanyName;
    /**
     *	String	是	支付公司海关备案编码（保税和直邮必填），一般贸易为“支付公司编码”
     **/
    private String payCommanyCode;
    /**
     *	String	否	支付原始请求回调响应报文（保税和直邮必填）
     **/
    private String payDecarleResponse;
    /**
     *	String	否	支付原始请求报文（保税和直邮必填）
     **/
    private String payDecarleRequest;
    /**
     *	String	是	海关申报支付单支付流水号（保税和直邮必填，且必须是支付公司推送海关申报的时候填写的支付流水号）
     **/
    private String paySerial;


    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getEnterpriseNum() {
        return enterpriseNum;
    }

    public void setEnterpriseNum(String enterpriseNum) {
        this.enterpriseNum = enterpriseNum;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getPayCommanyName() {
        return payCommanyName;
    }

    public void setPayCommanyName(String payCommanyName) {
        this.payCommanyName = payCommanyName;
    }

    public String getPayCommanyCode() {
        return payCommanyCode;
    }

    public void setPayCommanyCode(String payCommanyCode) {
        this.payCommanyCode = payCommanyCode;
    }

    public String getPayDecarleResponse() {
        return payDecarleResponse;
    }

    public void setPayDecarleResponse(String payDecarleResponse) {
        this.payDecarleResponse = payDecarleResponse;
    }

    public String getPayDecarleRequest() {
        return payDecarleRequest;
    }

    public void setPayDecarleRequest(String payDecarleRequest) {
        this.payDecarleRequest = payDecarleRequest;
    }

    public String getPaySerial() {
        return paySerial;
    }

    public void setPaySerial(String paySerial) {
        this.paySerial = paySerial;
    }
}
