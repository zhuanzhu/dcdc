package com.egeo.components.third.enums;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public enum ChannelServiceTypeEnum {
    REQ("req","请求"),
    RESP("resp","响应"),
    BACK("back","回调"),
    ;

    private String channelServiceType;

    private String msg;

    public String getChannelServiceType() {
        return channelServiceType;
    }

    public void setChannelServiceType(String channelServiceType) {
        this.channelServiceType = channelServiceType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private ChannelServiceTypeEnum(String channelServiceType, String msg) {
        this.channelServiceType = channelServiceType;
        this.msg = msg;
    }
}
