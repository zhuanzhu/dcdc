package com.egeo.components.product.condition;

import com.egeo.components.product.po.ProductPO;

/**
 * 
 * @author xiaping
 * @date 2017-07-17 11:21:23
 */
public class ProductCondition extends ProductPO {
	private static final long serialVersionUID = 1L;
	/**
	 * 产品描述
	 */
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	

}
	