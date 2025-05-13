package com.egeo.components.product.condition;

import com.egeo.components.product.po.SkuAttNamePO;

/**
 * 
 * @author min
 * @date 2018-01-06 09:08:12
 */
public class SkuAttNameCondition extends SkuAttNamePO {
	private static final long serialVersionUID = 1L;
	/**
	 * 属性
	 */
	private String name;
	/**
	 * 属性值
	 */
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
	