package com.egeo.components.product.condition;

import com.egeo.components.product.po.MerchantProductTagPO;

/**
 * 
 * @author min
 * @date 2018-05-11 15:04:03
 */
public class MerchantProductTagCondition extends MerchantProductTagPO {
	private static final long serialVersionUID = 1L;
	/**
	 * 标签名称
	 */
	private String tagName;
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	

}
	