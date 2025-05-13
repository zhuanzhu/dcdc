package com.egeo.components.third.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class EnterpriseServiceRouteRequestDTO implements Serializable {

    /**
     * 企业id
     **/
    private Integer enterpriseId;

    /**
     * 服务接口名称/接口类型参照枚举：ChannelServiceNameEnum.java
     **/
    private String channelServiceName;

    /**
     * 服务类型：req、resp、back参照枚举类：ChannelServiceTypeEnum.java
     **/
    private String channelServiceType;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public EnterpriseServiceRouteRequestDTO() {
    }

    public EnterpriseServiceRouteRequestDTO(Integer enterpriseId, String channelServiceName, String channelServiceType) {
        this.enterpriseId = enterpriseId;
        this.channelServiceName = channelServiceName;
        this.channelServiceType = channelServiceType;
    }
}
