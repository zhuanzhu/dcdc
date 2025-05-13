package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class skuAPP implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3677924391118149611L;
	private Long id;
	/**
	 * 属性值集合
	 */
	private List<String> valueList;
	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;
	/**
	 * sku库存
	 */
	private Long stock;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<String> getValueList() {
		return valueList;
	}
	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	
	
}
