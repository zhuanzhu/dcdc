package com.egeo.components.third.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 14:22
 * @Version V1.0
 **/
public class ChannelLogDTO implements Serializable {


    private Long id;

    /**
     * 企业id
     **/
    private String enterpriseId;

    private String channelCode;

    /**
     * 服务接口名称/接口类型：
     **/
    private String channelServiceName;

    /**
     * 服务类型：req、resp、back
     **/
    private String channelServiceType;

    private String bizCode;

    private String nextBizCode;

    private Integer state;

    private String requestJson;

    private String responseJson;

    private String responseCode;

    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 更新时间
     **/
    private Date updateTime;

    private String responseMsg;


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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
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

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public ChannelLogDTO(){

    }

    public ChannelLogDTO(RemoteExecuteDTO dto){
        this.channelCode = dto.getChannelCode();
        this.channelServiceName = dto.getChannelServiceName();
        this.channelServiceType = dto.getChannelServiceType();
        this.bizCode = dto.getBizCode();
        this.nextBizCode = dto.getNextBizCode();
        this.requestJson = dto.getJsonString();
        this.enterpriseId = dto.getEnterpriseId() !=null?String.valueOf(dto.getEnterpriseId()):null;
        this.state =0;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
