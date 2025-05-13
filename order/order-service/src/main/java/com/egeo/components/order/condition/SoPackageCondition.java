package com.egeo.components.order.condition;

import com.egeo.components.order.po.SoPackagePO;

/**
 * 
 * @author jiang
 * @date 2018-01-29 11:18:40
 */
public class SoPackageCondition extends SoPackagePO {
	private static final long serialVersionUID = 1L;
	/**
	 * 箱号
	 */
	private Long soBoxCode;
	public Long getSoBoxCode() {
		return soBoxCode;
	}
	public void setSoBoxCode(Long soBoxCode) {
		this.soBoxCode = soBoxCode;
	}	
	

}
	