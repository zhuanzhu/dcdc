package com.egeo.components.product.dto;

import java.io.Serializable;

public class SoItemSkuAttValueDTO implements Serializable{

	private static final long serialVersionUID = 1824136406917558731L;
	
	private Long skuId;
	private Long skuAttNameId;
	private String skuName;
	private Long skuAttValueId;
	private String attValueCustom;
	
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getSkuAttNameId() {
		return skuAttNameId;
	}
	public void setSkuAttNameId(Long skuAttNameId) {
		this.skuAttNameId = skuAttNameId;
	}
	public Long getSkuAttValueId() {
		return skuAttValueId;
	}
	public void setSkuAttValueId(Long skuAttValueId) {
		this.skuAttValueId = skuAttValueId;
	}
	public String getAttValueCustom() {
		return attValueCustom;
	}
	public void setAttValueCustom(String attValueCustom) {
		this.attValueCustom = attValueCustom;
	}
	
	
}
