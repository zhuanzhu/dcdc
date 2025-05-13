package com.egeo.components.third.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class PushOrderInfoDTO implements Serializable {


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
     * 业务类型：push_order
     **/
    private String bizType;
    /**
     * 推送时间
     **/
    private Date pushTime;
    /**
     * 业务编号
     **/
    private String bizNo;

    /**
     * 推送状态：0未推送 1推送中 2推送成功 3推送失败
     **/
    private Integer pushState;
    /**
     * 推送次数
     **/
    private Integer pushNum;
    /**
     * 推送结果body
     **/
    private String pushResult;
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

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }


    public Integer getPushState() {
        return pushState;
    }

    public void setPushState(Integer pushState) {
        this.pushState = pushState;
    }

    public Integer getPushNum() {
        return pushNum;
    }

    public void setPushNum(Integer pushNum) {
        this.pushNum = pushNum;
    }

    public String getPushResult() {
        return pushResult;
    }

    public void setPushResult(String pushResult) {
        this.pushResult = pushResult;
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
