package com.egeo.components.product.condition;

import java.math.BigDecimal;

import com.egeo.components.product.po.AttributeNamePO;

/**
 * 
 * @author xiaping
 * @date 2017-07-17 11:21:22
 */
public class AttributeNameCondition extends AttributeNamePO {
	private static final long serialVersionUID = 1L;

	/**
	 * 浮点型开始范围
	 */
	private BigDecimal beginDecimal;	

	/**
	 * 浮点型结束范围
	 */
	private BigDecimal finishDecimal;	

	/**
	 * 单位
	 */
	private String unit;

	public BigDecimal getBeginDecimal() {
		return beginDecimal;
	}

	public void setBeginDecimal(BigDecimal beginDecimal) {
		this.beginDecimal = beginDecimal;
	}

	public BigDecimal getFinishDecimal() {
		return finishDecimal;
	}

	public void setFinishDecimal(BigDecimal finishDecimal) {
		this.finishDecimal = finishDecimal;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}	
	
}
	