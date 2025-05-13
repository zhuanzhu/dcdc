package com.egeo.components.cms.vo;




import java.util.List;
import java.util.Map;

/**
 * 客户端cms模板结构vo
 * 
 * @author graci
 *
 */
public class TemplateConstructVO {
	public List<Map<String, Object>> getInstMapList() {
		return instMapList;
	}

	public void setInstMapList(List<Map<String, Object>> instMapList) {
		this.instMapList = instMapList;
	}


	/**
	 * 实例inst集合
	 */
	private List<Map<String, Object>> instMapList;


	/**
	 * 模板id
	 */
	private Long templateId;

	/**
	 * 模板类型 0:商城类 1:应用类
	 */
	private Integer templateType;

	/**
	 * 是否显示福管家logo
	 */
	private boolean showFgj;

	/**
	 * 是否显示合作方logo
	 */
	private boolean showCooperator;

	/**
	 * 合作方logo url
	 */
	private String coopUrl;

	private List<ElementConstructVO> elementList;

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public boolean getShowFgj() {
		return showFgj;
	}

	public void setShowFgj(boolean showFgj) {
		this.showFgj = showFgj;
	}

	public boolean getShowCooperator() {
		return showCooperator;
	}

	public void setShowCooperator(boolean showCooperator) {
		this.showCooperator = showCooperator;
	}

	public String getCoopUrl() {
		return coopUrl;
	}

	public void setCoopUrl(String coopUrl) {
		this.coopUrl = coopUrl;
	}

	public List<ElementConstructVO> getElementList() {
		return elementList;
	}

	public void setElementList(List<ElementConstructVO> elementList) {
		this.elementList = elementList;
	}

}
