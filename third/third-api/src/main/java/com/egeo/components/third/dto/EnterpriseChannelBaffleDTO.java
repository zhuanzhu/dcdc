package com.egeo.components.third.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class EnterpriseChannelBaffleDTO implements Serializable {


    /**
     * 主键
     **/
    private Long id;
    /**
     * 企业id
     **/
    private Integer enterpriseId;
    /**
     * 渠道编码
     **/
    private String channelCode;
    /**
     * 服务接口名称/接口类型
     **/
    private String channelServiceName;
    /**
     * 服务类型：req、resp、back
     **/
    private String channelServiceType;
    /**
     * 启用状态：0不启用 1启用 默认不启用
     **/
    private Integer state;
    /**
     * 返回数据
     **/
    private String returnData;

    /**
     * 是否加密：0不解密 1解密，默认不解密
     **/
    private Integer ifDecrypt;

    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 更新时间
     **/
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
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

    public Integer getIfDecrypt() {
        return ifDecrypt;
    }

    public void setIfDecrypt(Integer ifDecrypt) {
        this.ifDecrypt = ifDecrypt;
    }

    public EnterpriseChannelBaffleDTO() {
    }

    public EnterpriseChannelBaffleDTO(Integer enterpriseId, String channelCode, String channelServiceName, String channelServiceType, Integer state) {
        this.enterpriseId = enterpriseId;
        this.channelCode = channelCode;
        this.channelServiceName = channelServiceName;
        this.channelServiceType = channelServiceType;
        this.state = state;
    }
}
