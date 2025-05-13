package com.egeo.components.third.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class EnterpriseChannelServiceChoiceDTO implements Serializable {

    /**
     * 主键
     **/
    private Long id;
    /**
     * 企业id
     **/
    private String enterpriseId;
    /**
     * 服务接口名称/接口类型
     **/
    private String channelServiceName;
    /**
     * 服务类型：req、resp、back
     **/
    private String channelServiceType;
    /**
     * 规则策略名称
     **/
    private String ruteName;
    /**
     * 规则明细
     **/
    private String ruteDetail;
    /**
     * 启用状态：0不启用 1启用 默认不启用
     **/
    private Integer state;
    /**
     * 备注
     **/
    private String remarks;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 更新时间
     **/
    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
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

    public String getRuteName() {
        return ruteName;
    }

    public void setRuteName(String ruteName) {
        this.ruteName = ruteName;
    }

    public String getRuteDetail() {
        return ruteDetail;
    }

    public void setRuteDetail(String ruteDetail) {
        this.ruteDetail = ruteDetail;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
