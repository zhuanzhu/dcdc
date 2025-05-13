package com.egeo.components.cms.vo;

public class LinkableButtonPageVO {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * pageId
	 */
	private Long cmsPageId;
	
	/**
	 * 客户端类型
	 */
	private Integer clientType;
	
	private String pageName;

	public Long getCmsPageId() {
		return cmsPageId;
	}

	public void setCmsPageId(Long cmsPageId) {
		this.cmsPageId = cmsPageId;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	@Override
	public String toString() {
		return "LinkableButtonPageVO [cmsPageId=" + cmsPageId + ", clientType=" + clientType + "]";
	}

	public LinkableButtonPageVO(Long cmsPageId, Integer clientType, String pageName) {
		this.cmsPageId = cmsPageId;
		this.clientType = clientType;
		this.pageName = pageName;
	}

	public LinkableButtonPageVO() {

	}

	
}
