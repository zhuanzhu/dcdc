package com.egeo.components.product.vo;

import java.io.Serializable;

public class CategoryAttName implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2855009737596786915L;

	private Long id;
	
	private String name;
	
	/**
	 * 
	 */
	private Integer sortValue;
	
	/**
	 * 是否必填：0否、1是
	 */
	private Integer isRequired;
	
	private boolean checked;

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

	public Integer getSortValue() {
		return sortValue;
	}

	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	public Integer getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
