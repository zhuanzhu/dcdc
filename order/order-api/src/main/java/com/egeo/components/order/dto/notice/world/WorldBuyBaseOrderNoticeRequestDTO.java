package com.egeo.components.order.dto.notice.world;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldBuyBaseOrderNoticeRequestDTO implements Serializable {

    /**
     * goodsChange
     **/
    private String MessageType;

    /***
     * String 数组
     **/
    private Object data;

    /***
     * int 业务时间
     **/
    private Integer datetime;

    /**
     * 是	签名
     **/
    private String sign;

    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String messageType) {
        MessageType = messageType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getDatetime() {
        return datetime;
    }

    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }
}
