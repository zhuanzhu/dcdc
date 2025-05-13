package com.egeo.components.cms.vo;

import java.util.List;

/**
 * 后台查询模板详情VO
 * @author graci
 *
 */
public class TemplateDetailVO {

	private String name;
	
	private String remark;
	
	private Integer clientType;
	
	private Integer showFgj;
	
	private Integer type;
	
	private Integer companyType;
	
	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	private List<TmplElementListVO> elementList;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getShowFgj() {
		return showFgj;
	}

	public void setShowFgj(Integer showFgj) {
		this.showFgj = showFgj;
	}

	public List<TmplElementListVO> getElementList() {
		return elementList;
	}

	public void setElementList(List<TmplElementListVO> elementList) {
		this.elementList = elementList;
	}
	
	
}
