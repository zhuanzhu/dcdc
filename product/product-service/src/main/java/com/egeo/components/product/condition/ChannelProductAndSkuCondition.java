package com.egeo.components.product.condition;

import com.egeo.components.product.po.ChannelProductSkuPO;

import java.math.BigDecimal;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductAndSkuCondition extends ChannelProductSkuPO {
    /**
     *商品名称
     */
    private String chineseName;
    /**
     *品牌ID
     */
    private String brandId;
    /**
     *商品名称
     */
    private String title;
    /**
     *商品名称
     */
    private String name;
    /**
     *商品类型(0-一般贸易,1-保税,2-海外直邮)
     */
    private String goodsType;
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
     *明细
     */
    private String productDetails;
    /**
     *供应商
     */
    private String supplierId;

    /**
     *商品模版id
     */
    private String commodityTemplateId;
    /**
     *价格明细
     */
    private String priceDetail;

    /**
     * 商品主图
     */
    private String imagePath;


    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
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

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
