package com.egeo.components.product.vo;

import java.io.Serializable;

public class ValueAPP implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1733105156287477991L;
	
	private String value;
	
	private String pictureUrl;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

}
