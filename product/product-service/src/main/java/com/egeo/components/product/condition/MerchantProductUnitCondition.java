package com.egeo.components.product.condition;

import java.math.BigDecimal;


public class MerchantProductUnitCondition {

	private static final long serialVersionUID = 1L;

	private String merchantProductSerialNumber;

	private String merchantProductName;

	private String merchantProductCategory;

	private Long supplierId;

	private BigDecimal saleVolume;

	private String productUnitSerialNumber;

	private String productUnitName;

	private BigDecimal salePrice;

	private BigDecimal skuMarketPrice;

	private BigDecimal skuCostingPrice;

	private Long standardUnitId;

	private Integer isVisible;

    private Integer status;

	private BigDecimal taxRate;

	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}

	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
	}

	public String getMerchantProductName() {
		return merchantProductName;
	}

	public void setMerchantProductName(String merchantProductName) {
		this.merchantProductName = merchantProductName;
	}

	public String getMerchantProductCategory() {
		return merchantProductCategory;
	}

	public void setMerchantProductCategory(String merchantProductCategory) {
		this.merchantProductCategory = merchantProductCategory;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public BigDecimal getSaleVolume() {
		return saleVolume;
	}

	public void setSaleVolume(BigDecimal saleVolume) {
		this.saleVolume = saleVolume;
	}

	public String getProductUnitSerialNumber() {
		return productUnitSerialNumber;
	}

	public void setProductUnitSerialNumber(String productUnitSerialNumber) {
		this.productUnitSerialNumber = productUnitSerialNumber;
	}

	public String getProductUnitName() {
		return productUnitName;
	}

	public void setProductUnitName(String productUnitName) {
		this.productUnitName = productUnitName;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
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

	public Long getStandardUnitId() {
		return standardUnitId;
	}

	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
}
	