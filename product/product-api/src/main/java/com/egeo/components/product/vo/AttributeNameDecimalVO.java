package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author min
 * @date 2018-01-03 20:15:51
 */
public class AttributeNameDecimalVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 属性id
	 */
	private Long attributeNameId;
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

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 属性id
	 * @return 属性id
	 */
	public Long getAttributeNameId() {
		return attributeNameId;
	}

	/**
	 * 属性id
	 * @param attributeNameId 属性id
	 */
	public void setAttributeNameId(Long attributeNameId) {
		this.attributeNameId = attributeNameId;
	}	
	/**
	 * 浮点型开始范围
	 * @return 浮点型开始范围
	 */
	public BigDecimal getBeginDecimal() {
		return beginDecimal;
	}

	/**
	 * 浮点型开始范围
	 * @param beginDecimal 浮点型开始范围
	 */
	public void setBeginDecimal(BigDecimal beginDecimal) {
		this.beginDecimal = beginDecimal;
	}	
	/**
	 * 浮点型结束范围
	 * @return 浮点型结束范围
	 */
	public BigDecimal getFinishDecimal() {
		return finishDecimal;
	}

	/**
	 * 浮点型结束范围
	 * @param finishDecimal 浮点型结束范围
	 */
	public void setFinishDecimal(BigDecimal finishDecimal) {
		this.finishDecimal = finishDecimal;
	}	
	/**
	 * 单位
	 * @return 单位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 单位
	 * @param unit 单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}	
}
	