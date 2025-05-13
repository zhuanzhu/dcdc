package com.egeo.components.product.dto.channel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductDTO implements Serializable {
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
     *商品名称
     */
    private String chineseName;
    /**
     *三级分类ID
     */
    private String categoryId;
    /**
     *二级分类ID
     */
    private String categoryPid;
    /**
     *一级分类ID
     */
    private String categoryFPid;
    /**
     *品牌ID
     */
    private String brandId;
    /**
     *
     */
    private Long categoryTreeNodeId;
    /**
     *
     */
    private String title;
    /**
     *
     */
    private String name;
    /**
     *商品类型(0-一般贸易,1-保税,2-海外直邮)
     */
    private Integer goodsType;
    /**
     *品牌名称
     */
    private String brandName;
    /**
     *商品等级编码
     */
    private String goodsLevel;
    /**
     *市场价格
     */
    private BigDecimal marketPrice;

    /**
     *建议零售价
     */
    private BigDecimal price;

    /**
     *税号(海购商品必须有税号,普通商品无)
     */
    private String taxNo;
    /**
     *EAN(商品条形码)
     */
    private String eanNo;
    /**
     *产地
     */
    private String placeOfOrigin;
    /**
     *产地名称
     */
    private String countryName;
    /**
     *计量单位
     */
    private String calculationUnit;
    /**
     *单位名称
     */
    private String unitName;
    /**
     *状态:1:正常
     */
    private Integer status;
    /**
     *是否可用:默认0否;1是
     */
    private Integer isAvailable;
    /**
     *
     */
    private String productDetails;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;
    /**
     *
     */
    private Long supplierId;
    /**
     *创建的代理商
     */
    private Long enterpriseId;
    /**
     *平台id
     */
    private Long platformId;
    /**
     *商品模版id
     */
    private String commodityTemplateId;
    /**
     *价格明细
     */
    private String priceDetail;
    /**
     *
     */
    private String referlink;
    /**
     *
     */
    private String extend;

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

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryPid() {
        return categoryPid;
    }

    public void setCategoryPid(String categoryPid) {
        this.categoryPid = categoryPid;
    }

    public String getCategoryFPid() {
        return categoryFPid;
    }

    public void setCategoryFPid(String categoryFPid) {
        this.categoryFPid = categoryFPid;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Long getCategoryTreeNodeId() {
        return categoryTreeNodeId;
    }

    public void setCategoryTreeNodeId(Long categoryTreeNodeId) {
        this.categoryTreeNodeId = categoryTreeNodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGoodsLevel() {
        return goodsLevel;
    }

    public void setGoodsLevel(String goodsLevel) {
        this.goodsLevel = goodsLevel;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getEanNo() {
        return eanNo;
    }

    public void setEanNo(String eanNo) {
        this.eanNo = eanNo;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCalculationUnit() {
        return calculationUnit;
    }

    public void setCalculationUnit(String calculationUnit) {
        this.calculationUnit = calculationUnit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getCommodityTemplateId() {
        return commodityTemplateId;
    }

    public void setCommodityTemplateId(String commodityTemplateId) {
        this.commodityTemplateId = commodityTemplateId;
    }

    public String getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(String priceDetail) {
        this.priceDetail = priceDetail;
    }

    public String getReferlink() {
        return referlink;
    }

    public void setReferlink(String referlink) {
        this.referlink = referlink;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
