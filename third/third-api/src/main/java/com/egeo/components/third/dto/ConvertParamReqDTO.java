package com.egeo.components.third.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ConvertParamReqDTO implements Serializable {
    private String channelCode;
    private String channelServiceName;
    private String channelServiceType;
    private String jsonString;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelServiceName() {
        return channelServiceName;
    }

    public void setChannelServiceName(String channelServiceName) {
        this.channelServiceName = channelServiceName;
    }

    public String getChannelServiceType() {
        return channelServiceType;
    }

    public void setChannelServiceType(String channelServiceType) {
        this.channelServiceType = channelServiceType;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
