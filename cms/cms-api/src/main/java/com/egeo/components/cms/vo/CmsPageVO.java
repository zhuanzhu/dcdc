package com.egeo.components.cms.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author tan
 * @date 2018-12-13 16:58:48
 */
public class CmsPageVO implements Serializable {
	private static final long serialVersionUID = 1L;

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
	 * 客户端类型  5：PC  6： 移动
	 */
	private Integer clientType;
	
	/**
	 * 模板名称
	 */
	private String templateName;
	
	/**
	 * 配置项值JSON串
	 */
	private String configJson;
	
	/**
	 * 查询类型 1：商品列表页   0：不包含商品列表页
	 */
	private Integer searchType;
	
	/**
	 * 页面状态 1：停用  0：启用
	 */
	private Integer pageStatus;
	
	/**
	 * 配置状态 1：未完成 0：已完成
	 */
	private Integer cfgStatus;
	
	/**
	 * 根据公司名称模糊查询使用
	 */
	private List<Long> companyIds;
	
	/**
	 * 公司类型：0:正式公司 1:演示公司 2:竞品公司
	 */
	private Integer companyType;
	
	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 指定模板类型
	 */
	private Integer templateType;

	private Long companyId;
	
	/**
	 * 是否独立公司 1：是  0：否
	 */
	private Integer isSingle;

	/**
	 * 是否默认全部公司 1：是；0：否
	 */
	private Integer isDefault;
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
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

	public String getConfigJson() {
		return configJson;
	}

	public void setConfigJson(String configJson) {
		this.configJson = configJson;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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

	public List<Long> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public Integer getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(Integer isSingle) {
		this.isSingle = isSingle;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
}
	