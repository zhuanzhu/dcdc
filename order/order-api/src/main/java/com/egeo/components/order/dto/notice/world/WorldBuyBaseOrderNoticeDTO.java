package com.egeo.components.order.dto.notice.world;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldBuyBaseOrderNoticeDTO implements Serializable {

    /**
     * goodsChange
     **/
    private String MessageType;

    /***
     * String 数组
     **/
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
