package com.egeo.components.cms.condition;

import com.egeo.components.cms.po.CmsPageTabPO;

/**
 * 
 * @author mingqiang.luo
 * @date 2019-01-08 20:41:56
 */
public class CmsPageTabCondition extends CmsPageTabPO {
	private static final long serialVersionUID = 1L;
	/**
	 * 是否启用 1：启用 0：停用
	 */
	private Integer status;	

	/**
	 * 是否显示平台logo 1：显示 0：不显示
	 */
	private Integer showPlatformLogo;

	/**
	 * 是否显示客户Logo 1:显示 0:不显示
	 */
	private Integer showClientLogo;	
	
	/**
	 * 客户端类型
	 */
	private String clientType;	

	/**
	 * 平台Id
	 */
	private Long platformId;
	
	private Long companyId;
	
	private Long pageTabCompanyId;

	public Long getPageTabCompanyId() {
		return pageTabCompanyId;
	}

	public void setPageTabCompanyId(Long pageTabCompanyId) {
		this.pageTabCompanyId = pageTabCompanyId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getShowPlatformLogo() {
		return showPlatformLogo;
	}

	public void setShowPlatformLogo(Integer showPlatformLogo) {
		this.showPlatformLogo = showPlatformLogo;
	}

	public Integer getShowClientLogo() {
		return showClientLogo;
	}

	public void setShowClientLogo(Integer showClientLogo) {
		this.showClientLogo = showClientLogo;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
}
	