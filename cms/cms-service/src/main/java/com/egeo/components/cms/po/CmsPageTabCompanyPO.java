package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author mingqiang
 * @date 2019-01-16 11:33:28
 */
public class CmsPageTabCompanyPO {


	private Long id;

	/**
	 * tab页ID
	 */
	private Long pageTabId;	

	/**
	 * 是否启用 1：启用 0：停用
	 */
	private Integer status;	

	/**
	 * 是否显示平台logo1：显示 0：不显示
	 */
	private Integer showPlatformLogo;

	/**
	 * 是否显示企业logo 1：显示 0：不显示
	 */
	private Integer showClientLogo;	

	/**
	 * 客户端Id
	 */
	private Integer clientType;	

	/**
	 * 平台id
	 */
	private Integer platformId;	

	/**
	 * 公司Id
	 */
	private Long companyId;	

	/**
	 * 
	 */
	private Date createTime;	

	/**
	 * 
	 */
	private Date updateTime;	

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * tab页ID
	 * @return tab页ID
	 */
	public Long getPageTabId() {
		return pageTabId;
	}

	/**
	 * tab页ID
	 * @param pageTabId tab页ID
	 */
	public void setPageTabId(Long pageTabId) {
		this.pageTabId = pageTabId;
	}

	/**
	 * 是否启用 1：启用 0：停用
	 * @return 是否启用 1：启用 0：停用
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 是否启用 1：启用 0：停用
	 * @param status 是否启用 1：启用 0：停用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 是否显示平台logo1：显示 0：不显示
	 * @return 是否显示平台logo1：显示 0：不显示
	 */
	public Integer getShowPlatformLogo() {
		return showPlatformLogo;
	}

	/**
	 * 是否显示平台logo1：显示 0：不显示
	 * @param showPlatformLogo 是否显示平台logo1：显示 0：不显示
	 */
	public void setShowPlatformLogo(Integer showPlatformLogo) {
		this.showPlatformLogo = showPlatformLogo;
	}

	/**
	 * 是否显示企业logo 1：显示 0：不显示
	 * @return 是否显示企业logo 1：显示 0：不显示
	 */
	public Integer getShowClientLogo() {
		return showClientLogo;
	}

	/**
	 * 是否显示企业logo 1：显示 0：不显示
	 * @param showClientLogo 是否显示企业logo 1：显示 0：不显示
	 */
	public void setShowClientLogo(Integer showClientLogo) {
		this.showClientLogo = showClientLogo;
	}

	/**
	 * 客户端Id
	 * @return 客户端Id
	 */
	public Integer getClientType() {
		return clientType;
	}

	/**
	 * 客户端Id
	 * @param clientType 客户端Id
	 */
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	/**
	 * 平台id
	 * @return 平台id
	 */
	public Integer getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	/**
	 * 公司Id
	 * @return 公司Id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 公司Id
	 * @param companyId 公司Id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * 
	 * @return 
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 
	 * @return 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 * @param updateTime 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	