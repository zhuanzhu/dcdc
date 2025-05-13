package com.egeo.components.cms.vo;

/**
 * 客户端模板组件结构VO
 * @author graci
 *
 */
public class ElementConstructVO {

	/**
	 * 组件id
	 */
	private Long elementId;
	
	/**
	 * 组件类型 0:商城类 1:应用类 2:通用类
	 */
	private Integer elementType;
	
	/**
	 * 组件实例id
	 */
	private Long instId;
	
	/**
	 * 组件上边距
	 */
	private Integer instMargin;
	
	/**
	 * 配置类型 见com.egeo.core.Constant.ElementTypeConstant
	 */
	private Integer configType;

	public Integer getConfigType() {
		return configType;
	}

	public void setConfigType(Integer configType) {
		this.configType = configType;
	}

	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}

	public Integer getElementType() {
		return elementType;
	}

	public void setElementType(Integer elementType) {
		this.elementType = elementType;
	}

	public Long getInstId() {
		return instId;
	}

	public void setInstId(Long instId) {
		this.instId = instId;
	}

	public Integer getInstMargin() {
		return instMargin;
	}

	public void setInstMargin(Integer instMargin) {
		this.instMargin = instMargin;
	}
	
	
}
