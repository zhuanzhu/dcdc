package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MerchantProd implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4357025590151567949L;
	private Long id;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 以售数量
	 */
	private Long salesVolume;
	/**
	 * 图片
	 */
	private String picture;
	
	/**
     * 市场价格
     */
    private BigDecimal marketPrice;
    
    /**
	 * 销售价格
	 */
	private BigDecimal salePrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
}
