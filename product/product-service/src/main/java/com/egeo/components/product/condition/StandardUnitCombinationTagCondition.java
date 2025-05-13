package com.egeo.components.product.condition;

import com.egeo.components.product.po.StandardUnitCombinationTagPO;

/**
 * 
 * @author min
 * @date 2018-05-11 20:08:11
 */
public class StandardUnitCombinationTagCondition extends StandardUnitCombinationTagPO {
	private static final long serialVersionUID = 1L;
	/**
	 * 标签名称
	 */
	private Long tagName;
	public Long getTagName() {
		return tagName;
	}
	public void setTagName(Long tagName) {
		this.tagName = tagName;
	}	
	
}
	