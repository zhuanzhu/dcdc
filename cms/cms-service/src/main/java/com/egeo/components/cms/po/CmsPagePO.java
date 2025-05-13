package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-12-13 16:58:48
 */
public class CmsPagePO {


	private Long id;

	/**
	 * 模板ID
	 */
	private Long templateId;	

	/**
	 * 页面名称
	 */
	private String pageName;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * 
	 */
	private Long platformId;
	
	/**
	 * 页面状态
	 */
	private Integer pageStatus;
	
	/**
	 * 配置状态
	 */
	private Integer cfgStatus;
	
	/**
	 * 编辑人
	 */
	private Long updateUserId;
	/**
	 * 公司类型：0:正式公司 1:演示公司 2:竞品公司
	 */
	private Integer companyType;

	/**
	 * 公司ID
	 */
	private Long companyId;

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
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
	 * 模板ID
	 * @return 模板ID
	 */
	public Long getTemplateId() {
		return templateId;
	}

	/**
	 * 模板ID
	 * @param templateId 模板ID
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	/**
	 * 页面名称
	 * @return 页面名称
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * 页面名称
	 * @param pageName 页面名称
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
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
	 * 
	 * @return 
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 
	 * @param platformId 
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Integer getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(Integer pageStatus) {
		this.pageStatus = pageStatus;
	}

	public Integer getCfgStatus() {
		return cfgStatus;
	}

	public void setCfgStatus(Integer cfgStatus) {
		this.cfgStatus = cfgStatus;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
	