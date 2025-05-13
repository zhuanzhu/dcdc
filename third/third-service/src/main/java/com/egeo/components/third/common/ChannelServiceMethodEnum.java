package com.egeo.components.third.common;

import lombok.Getter;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public enum ChannelServiceMethodEnum {

    GET("get","正常的get请求"),
    POST_FORM("postForm","正常的post的form请求"),
    POST_JSON("postJson","正常的post的json请求"),
    HUTOOL_GET_BY_MAP("hutoolGetByMap","hutool插件正常的get请求,map作为入参"),
    HUTOOL_POST_BY_MAP("hutoolPostByMap","hutool插件正常的post请求,map作为入参"),

    ;

    @Getter
    private String channelServiceMethod;

    @Getter
    private String msg;

    private ChannelServiceMethodEnum(String channelServiceMethod, String msg) {
        this.channelServiceMethod = channelServiceMethod;
        this.msg = msg;
    }
}
