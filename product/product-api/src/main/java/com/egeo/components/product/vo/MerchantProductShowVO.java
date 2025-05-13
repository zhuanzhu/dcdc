package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MerchantProductShowVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1023876319480111341L;
	private Long frontSerialNumber;
	private Integer buyType;
	private Long id;
	private String merchantProductSerialNumber;	
	private Long merchantId;
	private Long standardProductUnitId;
	private String productCategory;
	private String name;
	private Integer isVisible;
	private BigDecimal salePrice;
	private BigDecimal demoSalePrice;
	private BigDecimal competingSalePrice;
	private Integer isVendible;
	private BigDecimal marketPrice;
	private BigDecimal promotionPrice;
	private Integer status;
	private Integer saleWay;
	private Long soldBase;
	private Long stockWarning;
	private Integer isSpuKeyword;
	private String content;
	private String freightExplain;
	private String shipmentsExplain;
	private String standardProductUnitName;
	private String productSerialNumber;
	private Long salesVolume;
	private List<Long> clientIds;
	private List<Long> companyIds = new ArrayList<>(); // 正式公司id集合
	private List<Long> demoCompanyIds = new ArrayList<>(); // 演示公司id集合
	private List<Long> competingCompanyIds = new ArrayList<>(); // 竞品公司id集合
	private Map<Long,String> merchantMap;

	public Map<Long, String> getMerchantMap() {
		return merchantMap;
	}

	public void setMerchantMap(Map<Long, String> merchantMap) {
		this.merchantMap = merchantMap;
	}
	/**
	 * 总店id
	 */
	private Long storeId;
	/**
	 * 预期值
	 */
	private Long presellPeriod;
	/**
	 * 关联su商品id
	 */
	private Long relevanceSuId;
	/**
	 * 关联su商品名称
	 */
	private String relevanceSuName;

	/**
	 * sku名称
	 * @return
	 */
	private String skuName;
	
	public String getRelevanceSuName() {
		return relevanceSuName;
	}
	public void setRelevanceSuName(String relevanceSuName) {
		this.relevanceSuName = relevanceSuName;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Long getPresellPeriod() {
		return presellPeriod;
	}
	public void setPresellPeriod(Long presellPeriod) {
		this.presellPeriod = presellPeriod;
	}
	public Long getRelevanceSuId() {
		return relevanceSuId;
	}
	public void setRelevanceSuId(Long relevanceSuId) {
		this.relevanceSuId = relevanceSuId;
	}
	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}
	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public BigDecimal getDemoSalePrice() {
		return demoSalePrice;
	}
	public void setDemoSalePrice(BigDecimal demoSalePrice) {
		this.demoSalePrice = demoSalePrice;
	}
	public BigDecimal getCompetingSalePrice() {
		return competingSalePrice;
	}
	public void setCompetingSalePrice(BigDecimal competingSalePrice) {
		this.competingSalePrice = competingSalePrice;
	}
	public Integer getIsVendible() {
		return isVendible;
	}
	public void setIsVendible(Integer isVendible) {
		this.isVendible = isVendible;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSaleWay() {
		return saleWay;
	}
	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}
	public Long getSoldBase() {
		return soldBase;
	}
	public void setSoldBase(Long soldBase) {
		this.soldBase = soldBase;
	}
	public Long getStockWarning() {
		return stockWarning;
	}
	public void setStockWarning(Long stockWarning) {
		this.stockWarning = stockWarning;
	}
	public Integer getIsSpuKeyword() {
		return isSpuKeyword;
	}
	public void setIsSpuKeyword(Integer isSpuKeyword) {
		this.isSpuKeyword = isSpuKeyword;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFreightExplain() {
		return freightExplain;
	}
	public void setFreightExplain(String freightExplain) {
		this.freightExplain = freightExplain;
	}
	public String getShipmentsExplain() {
		return shipmentsExplain;
	}
	public void setShipmentsExplain(String shipmentsExplain) {
		this.shipmentsExplain = shipmentsExplain;
	}
	public String getStandardProductUnitName() {
		return standardProductUnitName;
	}
	public void setStandardProductUnitName(String standardProductUnitName) {
		this.standardProductUnitName = standardProductUnitName;
	}
	public String getProductSerialNumber() {
		return productSerialNumber;
	}
	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}
	public Long getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
	}
	public List<Long> getClientIds() {
		return clientIds;
	}
	public void setClientIds(List<Long> clientIds) {
		this.clientIds = clientIds;
	}
	public List<Long> getCompanyIds() {
		return companyIds;
	}
	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
	}
	public List<Long> getDemoCompanyIds() {
		return demoCompanyIds;
	}
	public void setDemoCompanyIds(List<Long> demoCompanyIds) {
		this.demoCompanyIds = demoCompanyIds;
	}
	public List<Long> getCompetingCompanyIds() {
		return competingCompanyIds;
	}
	public void setCompetingCompanyIds(List<Long> competingCompanyIds) {
		this.competingCompanyIds = competingCompanyIds;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public Long getFrontSerialNumber() {
		return frontSerialNumber;
	}

	public void setFrontSerialNumber(Long frontSerialNumber) {
		this.frontSerialNumber = frontSerialNumber;
	}
	
}
