package com.egeo.components.product.condition;

import com.egeo.components.product.po.StandardProductUnitPO;

/**
 * 
 * @author min
 * @date 2018-01-05 18:51:01
 */
public class StandardProductUnitCondition extends StandardProductUnitPO {
	private static final long serialVersionUID = 1L;
	/**
	 * su序列号
	 */
	private int serialNumber;
	/**
	 * spu详情
	 */
	private String content;
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
	