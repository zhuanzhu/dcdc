package com.egeo.components.product.vo;

import java.util.HashMap;

public class JdProductStatStockAndPriceReqVO {
	
	private Long enterpriseId;
	private Long companyId;
	private HashMap<String,Integer> skuNumMap;
	private String addressStr;
	private Boolean price;
	private Boolean stock;
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public HashMap<String, Integer> getSkuNumMap() {
		return skuNumMap;
	}
	public void setSkuNumMap(HashMap<String, Integer> skuNumMap) {
		this.skuNumMap = skuNumMap;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	public Boolean getPrice() {
		return price;
	}
	public void setPrice(Boolean price) {
		this.price = price;
	}
	public Boolean getStock() {
		return stock;
	}
	public void setStock(Boolean stock) {
		this.stock = stock;
	}
	
}
