package com.egeo.components.product.vo;

import java.io.Serializable;

public class Value implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1733105156287477991L;
	
	private Long id;

	private String value;
	
	private String pictureUrl;
	
	private boolean checked;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
