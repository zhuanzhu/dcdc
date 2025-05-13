package com.egeo.components.product.condition;

import com.egeo.components.product.po.SellPlatformMerchantProdPO;

/**
 * 
 * @author min
 * @date 2018-01-06 14:42:44
 */
public class SellPlatformMerchantProdCondition extends SellPlatformMerchantProdPO {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 平台名称
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
	