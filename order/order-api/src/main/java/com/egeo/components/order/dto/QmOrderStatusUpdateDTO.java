package com.egeo.components.order.dto;

import com.egeo.utils.EmptyUtil;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 清美订单状态更新时通知收银台
 */
public class QmOrderStatusUpdateDTO implements Serializable {
    /**
     * appId
     */
    @NotBlank(message = "appId不可为空")
    private String appId;

    /**
     * 外部交易单号
     */
    @NotBlank(message = "outTradeNo不可为空")
    private String outTradeNo;

    /**
     * 子订单号
     */
    @NotBlank(message = "orderNo不可为空")
    private String orderNo;

    /**
     * 时间戳
     */
    @NotBlank(message = "timestamp不可为空")
    private String timestamp;

    /**
     * 订单状态，0：取消，30：待发货，40：完成
     */
    @NotBlank(message = "orderState不可为空")
    private String orderState;

    /**
     * 订单状态更新时间,格式（yyyy-MM-dd HH:mm:ss）
     */
    @NotBlank(message = "updateTime不可为空")
    private String updateTime;

    /**
     * 签名
     */
    @NotBlank(message = "sign不可为空")
    private String sign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Map<String,String> toMap(){
        Map<String,String> map=new HashMap<>();
        put( map,"appId",this.appId);
        put( map,"outTradeNo",this.outTradeNo);
        put( map,"orderNo",this.orderNo);
        put( map,"timestamp",this.timestamp);
        put( map,"orderState",this.orderState);
        put( map,"updateTime", this.updateTime);
        put( map,"sign",this.sign);
        return map;
    }

    public static void put(Map<String,String> map,String key,String value){
        if (EmptyUtil.isEmpty(key) || EmptyUtil.isEmpty(value)){
            return;
        }
        map.put(key,value);
    }
}
