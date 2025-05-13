package com.egeo.components.cms.vo;

public class TmplElementListVO {

	private Long elementId;
	
	private Long instId;
	
	private String imgUrl;
	
	private String elementName;
	
	private String clientVersionA;
	
	private String clientVersionI;

	public Long getInstId() {
		return instId;
	}

	public void setInstId(Long instId) {
		this.instId = instId;
	}

	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getClientVersionA() {
		return clientVersionA;
	}

	public void setClientVersionA(String clientVersionA) {
		this.clientVersionA = clientVersionA;
	}

	public String getClientVersionI() {
		return clientVersionI;
	}

	public void setClientVersionI(String clientVersionI) {
		this.clientVersionI = clientVersionI;
	}
	
	
}
