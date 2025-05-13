package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SellPlatformMerchantProdAPP implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7823080042821865660L;
	/**
	 * 平台名称
	 */
	private String sellPlatformName;
	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;	
	/**
	 * 链接
	 */
	private String path;
	/**
	 * 平台图片路径
	 */
	private String sellPlatformPictureUrl;
	public String getSellPlatformName() {
		return sellPlatformName;
	}
	public void setSellPlatformName(String sellPlatformName) {
		this.sellPlatformName = sellPlatformName;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSellPlatformPictureUrl() {
		return sellPlatformPictureUrl;
	}
	public void setSellPlatformPictureUrl(String sellPlatformPictureUrl) {
		this.sellPlatformPictureUrl = sellPlatformPictureUrl;
	}
	
}
