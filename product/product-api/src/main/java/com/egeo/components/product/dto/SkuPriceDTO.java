package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by 0.0 on 2019/4/18.
 */
public class SkuPriceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private Integer profit;
    private Long externalSkuId;
    private Long merchantProductId;
    private Long standardUnitId;
    private Long productUnitId;
    private Long commodityProductUnitId;
    private BigDecimal demoPrice;
    private BigDecimal competingSalePrice;
    private BigDecimal marketPrice;
    private BigDecimal sellPrice;

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public Long getExternalSkuId() {
        return externalSkuId;
    }

    public void setExternalSkuId(Long externalSkuId) {
        this.externalSkuId = externalSkuId;
    }

    public Long getMerchantProductId() {
        return merchantProductId;
    }

    public void setMerchantProductId(Long merchantProductId) {
        this.merchantProductId = merchantProductId;
    }

    public Long getStandardUnitId() {
        return standardUnitId;
    }

    public void setStandardUnitId(Long standardUnitId) {
        this.standardUnitId = standardUnitId;
    }

    public Long getProductUnitId() {
        return productUnitId;
    }

    public void setProductUnitId(Long productUnitId) {
        this.productUnitId = productUnitId;
    }

    public Long getCommodityProductUnitId() {
        return commodityProductUnitId;
    }

    public void setCommodityProductUnitId(Long commodityProductUnitId) {
        this.commodityProductUnitId = commodityProductUnitId;
    }

    public BigDecimal getDemoPrice() {
        return demoPrice;
    }

    public void setDemoPrice(BigDecimal demoPrice) {
        this.demoPrice = demoPrice;
    }

    public BigDecimal getCompetingSalePrice() {
        return competingSalePrice;
    }

    public void setCompetingSalePrice(BigDecimal competingSalePrice) {
        this.competingSalePrice = competingSalePrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }
}
