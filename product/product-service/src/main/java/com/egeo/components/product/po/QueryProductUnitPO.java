package com.egeo.components.product.po;

import com.egeo.entity.AbstractObject;

public class QueryProductUnitPO extends AbstractObject{
	/**
	 * 商品pu名称
	 */
	private String productUnitName;
	/**
	 * pu序列号
	 */
	private String productUnitSerialNumber;
	/**
	 * 门店名称
	 */
	private String storeName;
	/**
	 * 平台id
	 */
	private Long platformId;
	public String getProductUnitName() {
		return productUnitName;
	}
	public void setProductUnitName(String productUnitName) {
		this.productUnitName = productUnitName;
	}
	public String getProductUnitSerialNumber() {
		return productUnitSerialNumber;
	}
	public void setProductUnitSerialNumber(String productUnitSerialNumber) {
		this.productUnitSerialNumber = productUnitSerialNumber;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	
}
