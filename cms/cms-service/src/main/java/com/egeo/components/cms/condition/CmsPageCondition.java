package com.egeo.components.cms.condition;

import java.util.List;

import com.egeo.components.cms.po.CmsPagePO;

/**
 * 
 * @author tan
 * @date 2018-12-13 16:58:48
 */
public class CmsPageCondition extends CmsPagePO {
	private static final long serialVersionUID = 1L;

	private Integer clientType;
	
	private String templateName;
	
	/**
	 * 是否包含商品列表页
	 */
	private Integer searchType;
	
	private List<Long> companyIds;
	
	private Integer templateType;
	
	/**
	 * 
	 */
	private String clientVersionARemark;
	/**
	 * 
	 */
	private String clientVersionIRemark;
	
	private Integer clientVersionACode;
	
	private Integer clientVersionICode;
	
	private Long companyId;

	private Integer isSingle;

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public List<Long> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getClientVersionARemark() {
		return clientVersionARemark;
	}

	public void setClientVersionARemark(String clientVersionARemark) {
		this.clientVersionARemark = clientVersionARemark;
	}

	public String getClientVersionIRemark() {
		return clientVersionIRemark;
	}

	public void setClientVersionIRemark(String clientVersionIRemark) {
		this.clientVersionIRemark = clientVersionIRemark;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getClientVersionACode() {
		return clientVersionACode;
	}

	public void setClientVersionACode(Integer clientVersionACode) {
		this.clientVersionACode = clientVersionACode;
	}

	public Integer getClientVersionICode() {
		return clientVersionICode;
	}

	public void setClientVersionICode(Integer clientVersionICode) {
		this.clientVersionICode = clientVersionICode;
	}

	public Integer getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(Integer isSingle) {
		this.isSingle = isSingle;
	}
}
	