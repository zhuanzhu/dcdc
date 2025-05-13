package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelSupplierProductResponseVO implements Serializable {

    /**
     * 渠道:cake 蛋糕叔叔 worldBuy全球购
     **/
    private String channelCode;

    /**
     * 渠道分类节点/品牌id：蛋糕叔叔渠道中：品牌类别id(1-蛋糕,5-零食,8-鲜花)
     **/
    private String productType;

    private String productTypeName;

    /**
     * 节点id
     **/
    private String channelCategoryId;

    /**
     * 节点名称
     **/
    private String channelCategoryName;

    /**
     * 商品名称
     **/
    private String productName;

    private String productId;

    private String skuId;

    /**
     * 	String	是	结算价/协议价
     **/
    private BigDecimal price;

    private BigDecimal marketPrice;

    private BigDecimal profit;

    /**
     * 	销售价
     **/
    private BigDecimal salePrice;

    /**
     * 	状态 0下架 1上架
     **/
    private Integer status;

    /***;
     *	String	是	规格型号（目前暂时不支持）;
     **/
    private String specJson	;

    private String skuJson;

    /**
     * 商品主图
     */
    private String imagePath;

    private Integer state;

    /**
     * 来源：蛋糕叔叔-4，全球购-5
     */
    private Integer source;

    private Integer priceType;
    private Integer priceAudit;
    private String priceValue;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getChannelCategoryId() {
        return channelCategoryId;
    }

    public void setChannelCategoryId(String channelCategoryId) {
        this.channelCategoryId = channelCategoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSpecJson() {
        return specJson;
    }

    public void setSpecJson(String specJson) {
        this.specJson = specJson;
    }

    public String getSkuJson() {
        return skuJson;
    }

    public void setSkuJson(String skuJson) {
        this.skuJson = skuJson;
    }

    public BigDecimal getProfit() {
//        if(profit==null && marketPrice!=null &&price!=null) {
//            return marketPrice.subtract(price).divide(price,2,BigDecimal.ROUND_HALF_UP).intValue();
//        }
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getState() {
        if(state == null){
            return status;
        }
        return state;
    }

    public void setState(Integer state) {
        if(state == null){
            this.state = this.status;
        }else{
            this.state = state;
        }

    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Integer getPriceAudit() {
        return priceAudit;
    }

    public void setPriceAudit(Integer priceAudit) {
        this.priceAudit = priceAudit;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getChannelCategoryName() {
        return channelCategoryName;
    }

    public void setChannelCategoryName(String channelCategoryName) {
        this.channelCategoryName = channelCategoryName;
    }
}
