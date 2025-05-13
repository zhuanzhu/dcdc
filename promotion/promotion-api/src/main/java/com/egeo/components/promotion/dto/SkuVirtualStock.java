package com.egeo.components.promotion.dto;

import java.io.Serializable;

public class SkuVirtualStock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6199106977217924373L;
	/**
	 * skuid
	 */
	private Long skuId;
	/**
	 * sku进货数量
	 */
	private Long stockNum;
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getStockNum() {
		return stockNum;
	}
	public void setStockNum(Long stockNum) {
		this.stockNum = stockNum;
	}
	
}
