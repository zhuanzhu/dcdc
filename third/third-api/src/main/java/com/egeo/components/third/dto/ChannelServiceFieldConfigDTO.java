package com.egeo.components.third.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/6/20 15:56
 * @Version V1.0
 **/
public class ChannelServiceFieldConfigDTO implements Serializable {


    /**
     * 主键
     **/
    private Long id;
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
     * 源字段名称
     **/
    private String srcFieldName;
    /**
     * 源字段类型
     **/
    private String srcFieldType;
    /**
     * 源字段上级节点名称
     **/
    private String srcParentFieldName;
    /**
     * 字段描述
     **/
    private String fieldDescription;
    /**
     * '目标字段名称'
     **/
    private String targetFieldName;
    /**
     * 目标字段类型
     **/
    private String targetFieldType;
    /**
     * 目标字段上级节点名称
     **/
    private String targetParentFieldName;
    /**
     * 目标字段上级节点类型
     **/
    private String targetParentFieldType;

    /**
     * 检查规则-预留
     * [{"ruteValues":"Y","ruteName":"checkIsNull"}]
     */
    private String checkRute;

    /**
     * 格式化的规则集
     * [{"ruteValues":"1","ruteName":"minLen"},{"ruteValues":"yyyy-MM-dd HH:mm:ss","ruteName":"dateFormat"}]
     * [{"ruteValues":"100","ruteName":"maxLen"},{"ruteValues":"1","ruteName":"minLen"}]
     * [{"ruteValues":"2","ruteName":"fieldScale"}]
     * [{"ruteValues":[{"1":"2","3":"4"}],"ruteName":"convertStatus"}]
     */
    private String formatRute;
    /**
     * 启用状态：0不启用 1启用 默认不启用
     **/
    private Integer state;
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

    public String getSrcFieldName() {
        return srcFieldName;
    }

    public void setSrcFieldName(String srcFieldName) {
        this.srcFieldName = srcFieldName;
    }

    public String getSrcFieldType() {
        return srcFieldType;
    }

    public void setSrcFieldType(String srcFieldType) {
        this.srcFieldType = srcFieldType;
    }

    public String getSrcParentFieldName() {
        return srcParentFieldName;
    }

    public void setSrcParentFieldName(String srcParentFieldName) {
        this.srcParentFieldName = srcParentFieldName;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public String getTargetFieldName() {
        return targetFieldName;
    }

    public void setTargetFieldName(String targetFieldName) {
        this.targetFieldName = targetFieldName;
    }

    public String getTargetFieldType() {
        return targetFieldType;
    }

    public void setTargetFieldType(String targetFieldType) {
        this.targetFieldType = targetFieldType;
    }

    public String getTargetParentFieldName() {
        return targetParentFieldName;
    }

    public void setTargetParentFieldName(String targetParentFieldName) {
        this.targetParentFieldName = targetParentFieldName;
    }

    public String getTargetParentFieldType() {
        return targetParentFieldType;
    }

    public void setTargetParentFieldType(String targetParentFieldType) {
        this.targetParentFieldType = targetParentFieldType;
    }

    public String getCheckRute() {
        return checkRute;
    }

    public void setCheckRute(String checkRute) {
        this.checkRute = checkRute;
    }

    public String getFormatRute() {
        return formatRute;
    }

    public void setFormatRute(String formatRute) {
        this.formatRute = formatRute;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
