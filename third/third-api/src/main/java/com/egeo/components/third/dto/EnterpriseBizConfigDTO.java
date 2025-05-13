package com.egeo.components.third.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 企业渠道业务配置信息
 * @Author lsl
 * @Version V1.0
 **/
public class EnterpriseBizConfigDTO implements Serializable {

    /**
     * 主键
     **/
    private Long id;
    /**
     * 企业id
     **/
    private String enterpriseId;
    /**
     * 业务渠道code：渠道、供应商code
     **/
    private String bizCode;
    /**
     * 业务类型：push_order、getLogistics查询物流
     **/
    private String bizType;
    /**
     * 子业务类型：push_order
     **/
    private String smallBizType;
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
     * 渠道编码
     **/
    private Date createTime;
    /**
     * 渠道编码
     **/
    private Date updateTime;

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

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getSmallBizType() {
        return smallBizType;
    }

    public void setSmallBizType(String smallBizType) {
        this.smallBizType = smallBizType;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
