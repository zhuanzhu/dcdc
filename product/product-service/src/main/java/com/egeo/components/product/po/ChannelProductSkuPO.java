package com.egeo.components.product.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 渠道产品sku信息表
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductSkuPO implements Serializable {

    /**
     *
     */
    private Long id;
    /**
     *渠道code
     */
    private String channelCode;
    /**
     *商品ID
     */
    private String productId;
    /**
     *sku编号
     */
    private String skuSerialNumber;
    /**
     *
     */
    private Long merchantId;
    /**
     *spuid
     */
    private Long standardProductUnitId;
    /**
     *sku名称
     */
    private String skuName;
    /**
     *sku商品名称
     */
    private String skuProductName;
    /**
     *sku市场价格
     */
    private BigDecimal skuMarketPrice;

    /**
     *sku建议零售价格
     */
    private BigDecimal skuPrice;

    /**
     *参考成本价
     */
    private BigDecimal skuCostingPrice;
    /**
     *备注
     */
    private String remark;
    /**
     *是否可用:默认0否;1是
     */
    private Integer isAvailable;
    /**
     *是否有效: 0、否 1、是
     */
    private Integer isValid;
    /**
     *商品编码
     */
    private String code;
    /**
     *sku图片
     */
    private String skuPicUrl;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;
    /**
     *最后同步时间
     */
    private Date synchronizationTime;
    /**
     *平台id
     */
    private Long platformId;
    /**
     *外部sku_id
     */
    private String externalSkuId;
    /**
     *条形码
     */
    private String barCode;
    /**
     *sku编码
     */
    private String skuCode;

    /**
     *税率
     */
    private String taxRate;

    /**
     *是否含税（1-含税，0-不含税）
     */
    private Integer hasRate;

    /**
     *仓库明细
     */
    private String storeListText;

    private Integer state;

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

    public String getSkuSerialNumber() {
        return skuSerialNumber;
    }

    public void setSkuSerialNumber(String skuSerialNumber) {
        this.skuSerialNumber = skuSerialNumber;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getStandardProductUnitId() {
        return standardProductUnitId;
    }

    public void setStandardProductUnitId(Long standardProductUnitId) {
        this.standardProductUnitId = standardProductUnitId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuProductName() {
        return skuProductName;
    }

    public void setSkuProductName(String skuProductName) {
        this.skuProductName = skuProductName;
    }

    public BigDecimal getSkuMarketPrice() {
        return skuMarketPrice;
    }

    public void setSkuMarketPrice(BigDecimal skuMarketPrice) {
        this.skuMarketPrice = skuMarketPrice;
    }

    public BigDecimal getSkuCostingPrice() {
        return skuCostingPrice;
    }

    public void setSkuCostingPrice(BigDecimal skuCostingPrice) {
        this.skuCostingPrice = skuCostingPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSkuPicUrl() {
        return skuPicUrl;
    }

    public void setSkuPicUrl(String skuPicUrl) {
        this.skuPicUrl = skuPicUrl;
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

    public Date getSynchronizationTime() {
        return synchronizationTime;
    }

    public void setSynchronizationTime(Date synchronizationTime) {
        this.synchronizationTime = synchronizationTime;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getExternalSkuId() {
        return externalSkuId;
    }

    public void setExternalSkuId(String externalSkuId) {
        this.externalSkuId = externalSkuId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getHasRate() {
        return hasRate;
    }

    public void setHasRate(Integer hasRate) {
        this.hasRate = hasRate;
    }

    public String getStoreListText() {
        return storeListText;
    }

    public void setStoreListText(String storeListText) {
        this.storeListText = storeListText;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
