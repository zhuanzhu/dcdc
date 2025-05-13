package com.egeo.components.order.bean;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/9 17:34
 * @Version V1.0
 **/
public class NoticeReqHelpBean implements Serializable {

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
