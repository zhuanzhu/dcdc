package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2019-03-29 10:17:51
 */
public class JdUnsearchCategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String category;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
	