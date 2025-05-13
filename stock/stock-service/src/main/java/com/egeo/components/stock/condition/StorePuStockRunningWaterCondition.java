package com.egeo.components.stock.condition;

import com.egeo.components.stock.po.StorePuStockRunningWaterPO;

/**
 * 
 * @author min
 * @date 2018-09-13 05:39:58
 */
public class StorePuStockRunningWaterCondition extends StorePuStockRunningWaterPO {
	private static final long serialVersionUID = 1L;
	
	private String dictName;

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
}
	