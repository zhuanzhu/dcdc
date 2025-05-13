package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelPriceDTO implements Serializable {


    /**
     * id
     */
    private Long id;

    /**
     * pId 产品id
     */
    private String pid;

    /**
     * spuId 产品skuId或规格id
     */
    private String spuId;

    /**
     * 1.增加固定值 2.增加固定比例 3.固定价格
     */
    private Integer priceType;

    /**
     * 价格值
     */
    private String priceValue;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 渠道code
     */
    private String channelCode;

    private Integer audit=1;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getPriceType() {
        return priceType;
    }
    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }
    public String getPriceValue() {
        return priceValue;
    }
    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }
    public Long getEnterpriseId() {
        return enterpriseId;
    }
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }
}
