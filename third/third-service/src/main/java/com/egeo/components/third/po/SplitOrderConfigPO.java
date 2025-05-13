package com.egeo.components.third.po;

import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class SplitOrderConfigPO {

    /**
     * 主键
     **/
    private Long id;
    /**
     * 企业id
     **/
    private String enterpriseId;
    /**
     * 拆单规则策略名称:sale销售方式、pr供应商、spu单个商品
     **/
    private String splitRuteName;
    /**
     * '拆单规则明细
     **/
    private String splitRuteDetail;
    /**
     * '运费规则策略名称:平均,最后一单减法'
     **/
    private String freightRuteName;
    /**
     * 运费规则明细
     **/
    private String freightRuteDetail;
    /**
     * '启用状态：0不启用 1启用 默认不启用'
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

    public String getSplitRuteName() {
        return splitRuteName;
    }

    public void setSplitRuteName(String splitRuteName) {
        this.splitRuteName = splitRuteName;
    }

    public String getSplitRuteDetail() {
        return splitRuteDetail;
    }

    public void setSplitRuteDetail(String splitRuteDetail) {
        this.splitRuteDetail = splitRuteDetail;
    }

    public String getFreightRuteName() {
        return freightRuteName;
    }

    public void setFreightRuteName(String freightRuteName) {
        this.freightRuteName = freightRuteName;
    }

    public String getFreightRuteDetail() {
        return freightRuteDetail;
    }

    public void setFreightRuteDetail(String freightRuteDetail) {
        this.freightRuteDetail = freightRuteDetail;
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
