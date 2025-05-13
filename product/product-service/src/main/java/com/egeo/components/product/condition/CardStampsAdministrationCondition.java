package com.egeo.components.product.condition;

import com.egeo.components.product.po.CardStampsAdministrationPO;

/**
 * 
 * @author min
 * @date 2018-01-26 09:06:16
 */
public class CardStampsAdministrationCondition extends CardStampsAdministrationPO {
	private static final long serialVersionUID = 1L;
	/**
	 * su商品名称
	 */
	private String standardUnitName;
	/**
	 * 前端类目名称
	 */
	private String leadingEndCategoryName;
	public String getStandardUnitName() {
		return standardUnitName;
	}
	public void setStandardUnitName(String standardUnitName) {
		this.standardUnitName = standardUnitName;
	}
	public String getLeadingEndCategoryName() {
		return leadingEndCategoryName;
	}
	public void setLeadingEndCategoryName(String leadingEndCategoryName) {
		this.leadingEndCategoryName = leadingEndCategoryName;
	}
	
}
	