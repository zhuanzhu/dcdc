package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;


public class MerchantProductExportVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String merchantProductSerialNumber;

	private String merchantProductName;

	private String merchantProductCategory;

	private String supplierName;

	private Long supplierId;

	private BigDecimal saleVolume;

	private String productUnitSerialNumber;

	private String productUnitName;

	private BigDecimal salePrice;

	private BigDecimal skuMarketPrice;

	private BigDecimal skuCostingPrice;

	private String profit;

	private String companies;

	private String isVisible;

    private String status;

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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getCompanies() {
		return companies;
	}

	public void setCompanies(String companies) {
		this.companies = companies;
	}

	public String getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
}
	