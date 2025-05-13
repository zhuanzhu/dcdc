package com.egeo.components.product.condition;

import java.math.BigDecimal;

import com.egeo.components.product.po.CommodityProductUnitPO;

/**
 * 
 * @author min
 * @date 2018-01-08 11:16:12
 */
public class CommodityProductUnitCondition extends CommodityProductUnitPO {
	private static final long serialVersionUID = 1L;
	/**
	 * spuid
	 */
	private Long standardProductUnitId;	

	/**
	 * suid
	 */
	private Long standardUnitId;
	/**
	 * 商家id
	 */
	private Long merchantId;
	/**
	 * 商品模版id
	 */
	private Long commodityTemplateId;
	/**
	 * 总店id
	 */
	private Long storeId;
	/**
	 * 总店名称
	 */
	private String storeName;
	/**
	 * su商品名称
	 */
	private String standardUnitName;
	/**
	 * 是否可见：默认0是;1否
	 */
	private Integer isVisible;	
	/**
	 * 外部SKU ID
	 */
	private String externalSkuId;
	/**
	 * SKU名称
	 */
	private String skuName;
	
	private Long puId;
	private Long enterpriseId;
	
	private BigDecimal puSalePrice;
	/**
	 * SU下PU的最大销售价格
	 */
	private BigDecimal maxSalePrice;
	/**
	 * SU下PU的最小销售价格
	 */
	private BigDecimal minSalePrice;

    private String productCategory;

	/**
	 * 商品编码
	 */
	private String merchantProductSerialNumber;
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Long getPuId() {
		return puId;
	}
	public void setPuId(Long puId) {
		this.puId = puId;
	}
	public BigDecimal getPuSalePrice() {
		return puSalePrice;
	}
	public void setPuSalePrice(BigDecimal puSalePrice) {
		this.puSalePrice = puSalePrice;
	}
	public BigDecimal getMaxSalePrice() {
		return maxSalePrice;
	}
	public void setMaxSalePrice(BigDecimal maxSalePrice) {
		this.maxSalePrice = maxSalePrice;
	}
	public BigDecimal getMinSalePrice() {
		return minSalePrice;
	}
	public void setMinSalePrice(BigDecimal minSalePrice) {
		this.minSalePrice = minSalePrice;
	}
	public String getExternalSkuId() {
		return externalSkuId;
	}
	public void setExternalSkuId(String externalSkuId) {
		this.externalSkuId = externalSkuId;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getStandardUnitName() {
		return standardUnitName;
	}
	public void setStandardUnitName(String standardUnitName) {
		this.standardUnitName = standardUnitName;
	}
	public Integer getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}
	public Long getCommodityTemplateId() {
		return commodityTemplateId;
	}
	public void setCommodityTemplateId(Long commodityTemplateId) {
		this.commodityTemplateId = commodityTemplateId;
	}
	public Long getStandardProductUnitId() {
		return standardProductUnitId;
	}
	public void setStandardProductUnitId(Long standardProductUnitId) {
		this.standardProductUnitId = standardProductUnitId;
	}
	public Long getStandardUnitId() {
		return standardUnitId;
	}
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}

	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
	}
}
	