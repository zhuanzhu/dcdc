package com.egeo.components.third.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelServiceConfigDTO implements Serializable {

    private Long id;
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
     * 请求方式:get、postForm、postJson
     **/
    private String channelServiceMethod;

    /**
     * 请求url
     **/
    private String channelServiceUrl;

    /**
     * 是否加密：0不加密 1加密，默认不加密
     **/
    private Integer ifEncrypted;

    /**
     * 响应是否解密：0不解密 1解密，默认不解密
     **/
    private Integer ifDecrypt;

    /**
     * 加密方式,按照加密策略进行加密
     **/
    private String encryptType;

    /**
     *启用状态：0不启用 1启用 默认不启用
     **/
    private Integer state;

    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 更新时间
     **/
    private Date updateTime;

    private Integer ifLog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getChannelServiceMethod() {
        return channelServiceMethod;
    }

    public void setChannelServiceMethod(String channelServiceMethod) {
        this.channelServiceMethod = channelServiceMethod;
    }

    public String getChannelServiceUrl() {
        return channelServiceUrl;
    }

    public void setChannelServiceUrl(String channelServiceUrl) {
        this.channelServiceUrl = channelServiceUrl;
    }

    public Integer getIfEncrypted() {
        return ifEncrypted;
    }

    public void setIfEncrypted(Integer ifEncrypted) {
        this.ifEncrypted = ifEncrypted;
    }

    public Integer getIfDecrypt() {
        return ifDecrypt;
    }

    public void setIfDecrypt(Integer ifDecrypt) {
        this.ifDecrypt = ifDecrypt;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ChannelServiceConfigDTO() {
    }

    public ChannelServiceConfigDTO(String channelCode, String channelServiceName, String channelServiceType) {
        this.channelCode = channelCode;
        this.channelServiceName = channelServiceName;
        this.channelServiceType = channelServiceType;
    }

    public ChannelServiceConfigDTO(String channelCode, String channelServiceName, String channelServiceType, Integer state) {
        this.channelCode = channelCode;
        this.channelServiceName = channelServiceName;
        this.channelServiceType = channelServiceType;
        this.state = state;
    }

    public Integer getIfLog() {
        return ifLog;
    }

    public void setIfLog(Integer ifLog) {
        this.ifLog = ifLog;
    }
}
