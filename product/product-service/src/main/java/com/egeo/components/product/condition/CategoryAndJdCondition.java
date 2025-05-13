package com.egeo.components.product.condition;

import com.egeo.components.product.po.CategoryPO;

/**
 * 
 * @author tan
 * @date 2019-03-26 10:43:15
 */
public class CategoryAndJdCondition extends CategoryPO {
	private static final long serialVersionUID = 1L;
	private String jdName;
	private Long jdCategory;
	public String getJdName() {
		return jdName;
	}
	public void setJdName(String jdName) {
		this.jdName = jdName;
	}
	public Long getJdCategory() {
		return jdCategory;
	}
	public void setJdCategory(Long jdCategory) {
		this.jdCategory = jdCategory;
	}
	
}
	