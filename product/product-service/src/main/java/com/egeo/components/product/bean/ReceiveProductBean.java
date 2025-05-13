package com.egeo.components.product.bean;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/24 14:52
 * @Version V1.0
 **/
public class ReceiveProductBean implements Serializable {


    private Object  paramObject;

    private String channelCode;


    public Object getParamObject() {
        return paramObject;
    }

    public void setParamObject(Object paramObject) {
        this.paramObject = paramObject;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
}
