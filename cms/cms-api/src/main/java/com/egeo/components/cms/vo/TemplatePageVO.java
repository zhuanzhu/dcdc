package com.egeo.components.cms.vo;

/**
 * 后台模板分页列表VO
 * @author graci
 *
 */
public class TemplatePageVO {

	private Long templateId;
	
	private String templateName;
	
	private Integer status;
	
	private Integer clientType;
	
	private String clientVersionA;
	
	private String clientVersionI;
	
	private String remark;

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
