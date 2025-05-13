package com.egeo.components.product.condition;

import com.egeo.components.product.po.StandardProductUnitAttValuePO;

/**
 * 
 * @author min
 * @date 2018-01-06 13:00:30
 */
public class StandardProductUnitAttValueCondition extends StandardProductUnitAttValuePO {
	private static final long serialVersionUID = 1L;
	/**
	 * 属性值名称
	 */
	private String attValue;
	/**
	 * 属性id
	 */
	private Long attNameId;
	public String getAttValue() {
		return attValue;
	}
	public void setAttValue(String attValue) {
		this.attValue = attValue;
	}
	public Long getAttNameId() {
		return attNameId;
	}
	public void setAttNameId(Long attNameId) {
		this.attNameId = attNameId;
	}
	

}
	