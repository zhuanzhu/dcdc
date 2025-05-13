package com.egeo.components.cms.vo;

/**
 * 组件详情VO
 * 
 * @author graci
 *
 */
public class ElementDetailVO {

	private String elementName;
	private Integer margin;
	private String imgUrl;
	private Long elementId;
	private Long instId;
	private Long[] companyIds;
	private Long elementDictId;
	// configType
	private Integer type;
	
	private Long pageTabId;

	public Long getElementDictId() {
		return elementDictId;
	}

	public void setElementDictId(Long elementDictId) {
		this.elementDictId = elementDictId;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public Integer getMargin() {
		return margin;
	}

	public void setMargin(Integer margin) {
		this.margin = margin;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}

	public Long getInstId() {
		return instId;
	}

	public void setInstId(Long instId) {
		this.instId = instId;
	}

	public Long[] getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(Long[] companyIds) {
		this.companyIds = companyIds;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getPageTabId() {
		return pageTabId;
	}

	public void setPageTabId(Long pageTabId) {
		this.pageTabId = pageTabId;
	}

}
