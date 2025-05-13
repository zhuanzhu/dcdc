package com.egeo.components.stock.condition;

import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;

/**
 * 
 * @author min
 * @date 2018-05-17 17:00:02
 */
public class CommodityProductUnitStockRunningWaterCondition extends CommodityProductUnitStockRunningWaterPO {
	private static final long serialVersionUID = 1L;

	private String contactGroupStockName;

	public String getContactGroupStockName() {
		return contactGroupStockName;
	}

	public void setContactGroupStockName(String contactGroupStockName) {
		this.contactGroupStockName = contactGroupStockName;
	}
	
}
	