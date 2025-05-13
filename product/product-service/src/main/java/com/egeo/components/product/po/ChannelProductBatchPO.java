package com.egeo.components.product.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 渠道产品批次
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductBatchPO implements Serializable {
    /**
     *
     */
    private Long id;
    /**
     *渠道code
     */
    private String channelCode;
    /**
     *产品Id
     */
    private String productId;
    /**
     *skuId
     */
    private String linkSkuId;
    /**
     *批次库存数量
     */
    private Integer num;
    /**
     *批次号
     */
    private String batchNo;
    /**
     *关务批次ID（专用）
     */
    private String batchId;
    /**
     *固定1
     */
    private Integer status;
    /**
     *规则组id
     */
    private String specList;
    /**
     *规格件装
     */
    private String specNum;
    /**
     *规格件装名称
     */
    private String specName;
    /**
     *有效期
     */
    private String expiredDate;
    /**
     *生成日期
     */
    private String makeDate;
    /**
     *建议零售价
     */
    private BigDecimal price;
    /**
     *控价
     */
    private BigDecimal priceControl;
    /**
     *会员价
     */
    private BigDecimal priceVip;
    /**
     *结算价
     */
    private BigDecimal priceSettleMent;
    /**
     *市场价
     */
    private String priceMarket;
    /**
     *是否是最小价格元素
     */
    private Integer isMinStockSpecificationItem;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;
    /**
     *平台id
     */
    private Long platformId;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLinkSkuId() {
        return linkSkuId;
    }

    public void setLinkSkuId(String linkSkuId) {
        this.linkSkuId = linkSkuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSpecList() {
        return specList;
    }

    public void setSpecList(String specList) {
        this.specList = specList;
    }

    public String getSpecNum() {
        return specNum;
    }

    public void setSpecNum(String specNum) {
        this.specNum = specNum;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceControl() {
        return priceControl;
    }

    public void setPriceControl(BigDecimal priceControl) {
        this.priceControl = priceControl;
    }

    public BigDecimal getPriceVip() {
        return priceVip;
    }

    public void setPriceVip(BigDecimal priceVip) {
        this.priceVip = priceVip;
    }

    public BigDecimal getPriceSettleMent() {
        return priceSettleMent;
    }

    public void setPriceSettleMent(BigDecimal priceSettleMent) {
        this.priceSettleMent = priceSettleMent;
    }

    public String getPriceMarket() {
        return priceMarket;
    }

    public void setPriceMarket(String priceMarket) {
        this.priceMarket = priceMarket;
    }

    public Integer getIsMinStockSpecificationItem() {
        return isMinStockSpecificationItem;
    }

    public void setIsMinStockSpecificationItem(Integer isMinStockSpecificationItem) {
        this.isMinStockSpecificationItem = isMinStockSpecificationItem;
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

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
}
