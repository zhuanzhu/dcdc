package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-08-01 12:25:54
 */
public class PageTabPO {


	private Long id;

	/**
	 * 分页tab名称
	 */
	private String pageTabName;	

	/**
	 * 分页tab备注
	 */
	private String pageTabRemark;	

	/**
	 * tab排序号  -1:空值占位
	 */
	private Integer pageTabSort;	

	/**
	 * 是否设为首页 0:否 1:是
	 */
	private Integer isHomePage;	

	/**
	 * 分页tab状态 0:停用 1:启用
	 */
	private Integer pageTabStatus;	

	/**
	 * web商城模板id -1:空值占位
	 */
	private Long webTemplateId;	

	/**
	 * 所属企业id  -1:空值占位
	 */
	private Long companyId;	
	
	/**
	 * 更新人
	 */
	private Long updateUser;

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 平台id
	 */
	private Long platformId;

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

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
	 * 分页tab名称
	 * @return 分页tab名称
	 */
	public String getPageTabName() {
		return pageTabName;
	}

	/**
	 * 分页tab名称
	 * @param pageTabName 分页tab名称
	 */
	public void setPageTabName(String pageTabName) {
		this.pageTabName = pageTabName;
	}

	/**
	 * 分页tab备注
	 * @return 分页tab备注
	 */
	public String getPageTabRemark() {
		return pageTabRemark;
	}

	/**
	 * 分页tab备注
	 * @param pageTabRemark 分页tab备注
	 */
	public void setPageTabRemark(String pageTabRemark) {
		this.pageTabRemark = pageTabRemark;
	}

	/**
	 * tab排序号  -1:空值占位
	 * @return tab排序号  -1:空值占位
	 */
	public Integer getPageTabSort() {
		return pageTabSort;
	}

	/**
	 * tab排序号  -1:空值占位
	 * @param pageTabSort tab排序号  -1:空值占位
	 */
	public void setPageTabSort(Integer pageTabSort) {
		this.pageTabSort = pageTabSort;
	}

	/**
	 * 是否设为首页 0:否 1:是
	 * @return 是否设为首页 0:否 1:是
	 */
	public Integer getIsHomePage() {
		return isHomePage;
	}

	/**
	 * 是否设为首页 0:否 1:是
	 * @param isHomePage 是否设为首页 0:否 1:是
	 */
	public void setIsHomePage(Integer isHomePage) {
		this.isHomePage = isHomePage;
	}

	/**
	 * 分页tab状态 0:停用 1:启用
	 * @return 分页tab状态 0:停用 1:启用
	 */
	public Integer getPageTabStatus() {
		return pageTabStatus;
	}

	/**
	 * 分页tab状态 0:停用 1:启用
	 * @param pageTabStatus 分页tab状态 0:停用 1:启用
	 */
	public void setPageTabStatus(Integer pageTabStatus) {
		this.pageTabStatus = pageTabStatus;
	}

	/**
	 * web商城模板id -1:空值占位
	 * @return web商城模板id -1:空值占位
	 */
	public Long getWebTemplateId() {
		return webTemplateId;
	}

	/**
	 * web商城模板id -1:空值占位
	 * @param webTemplateId web商城模板id -1:空值占位
	 */
	public void setWebTemplateId(Long webTemplateId) {
		this.webTemplateId = webTemplateId;
	}

	/**
	 * 所属企业id  -1:空值占位
	 * @return 所属企业id  -1:空值占位
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 所属企业id  -1:空值占位
	 * @param companyId 所属企业id  -1:空值占位
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
}
	