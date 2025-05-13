package com.egeo.components.stock.vo;

import java.io.Serializable;

public class MerchantProductStock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7929366777390519362L;

	private Long id;
	
	private String name;
	
	private Long stock;

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

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}
	
}
