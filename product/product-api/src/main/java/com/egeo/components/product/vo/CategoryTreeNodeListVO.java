package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.List;

public class CategoryTreeNodeListVO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long categoryTreeId;
	private List<CategoryTreeNodeVO> list;
	public Long getCategoryTreeId() {
		return categoryTreeId;
	}
	public void setCategoryTreeId(Long categoryTreeId) {
		this.categoryTreeId = categoryTreeId;
	}
	public List<CategoryTreeNodeVO> getList() {
		return list;
	}
	public void setList(List<CategoryTreeNodeVO> list) {
		this.list = list;
	}
	
	

	
	
}
