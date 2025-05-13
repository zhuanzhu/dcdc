package com.egeo.components.third.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class RemoteExecuteDTO implements Serializable {

    /**
     * 企业ID
     **/
    private Integer enterpriseId;

    /**
     * 渠道编码
     **/
    private String channelCode;

    /**
     * 服务接口名称/接口类型：
     **/
    private String channelServiceName;

    /**
     * 服务类型：req、resp、back
     **/
    private String channelServiceType;

    /**
     * 请求的数据字符串
     **/
    private String jsonString;

    private String bizCode;

    private String nextBizCode;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

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

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getNextBizCode() {
        return nextBizCode;
    }

    public void setNextBizCode(String nextBizCode) {
        this.nextBizCode = nextBizCode;
    }
}
