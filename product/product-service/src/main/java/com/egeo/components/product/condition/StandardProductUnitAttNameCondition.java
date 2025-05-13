package com.egeo.components.product.condition;

import com.egeo.components.product.po.StandardProductUnitAttNamePO;

/**
 * 
 * @author min
 * @date 2018-01-05 18:51:02
 */
public class StandardProductUnitAttNameCondition extends StandardProductUnitAttNamePO {
	private static final long serialVersionUID = 1L;
	/**
	 * 属性名称
	 */
	private String attName;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 属性值填写方式：1是下拉单选框,2文本输入框，3列表复选框，4日期型、5整型、6数字型
	 */
	private int mode;
	public String getAttName() {
		return attName;
	}
	public void setAttName(String attName) {
		this.attName = attName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	

}
	