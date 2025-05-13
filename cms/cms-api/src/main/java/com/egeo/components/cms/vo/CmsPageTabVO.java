package com.egeo.components.cms.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mingqiang.luo
 * @date 2019-01-08 20:41:57
 */
public class CmsPageTabVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * tab页
	 */
	private String name;
	/**
	 * tab页手动赋值
	 */
	private String value;
	/**
	 * tab页默认值
	 */
	private String defaultValue;
	/**
	 * 图标url
	 */
	private String iconUrl;
	/**
	 * 图标默认url
	 */
	private String iconDefaultUrl;
	/**
	 * 图标url
	 */
	private String iconOnclickUrl;
	/**
	 * 图标默认url
	 */
	private String iconOnclickDefaultUrl;

	private String pageTabUrl;
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
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 客户端类型
	 */
	private String clientType;
	/**
	 * 平台Id
	 */
	private Long platformId;
	
	private Integer sort;
	/**
	 * tab页类型 1：首页
	 */
	private String type;
	
	private Long companyId;

	public Long getId() {
		return id;
	}

	/**
	 * 主键Id
	 * @param id 主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * tab页
	 * @return tab页
	 */
	public String getName() {
		return name;
	}

	/**
	 * tab页
	 * @param name tab页
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * tab页手动赋值
	 * @return tab页手动赋值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * tab页手动赋值
	 * @param value tab页手动赋值
	 */
	public void setValue(String value) {
		this.value = value;
	}	
	/**
	 * tab页默认值
	 * @return tab页默认值
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * tab页默认值
	 * @param defaultValue tab页默认值
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}	
	/**
	 * 图标url
	 * @return 图标url
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * 图标url
	 * @param iconUrl 图标url
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}	
	/**
	 * 图标默认url
	 * @return 图标默认url
	 */
	public String getIconDefaultUrl() {
		return iconDefaultUrl;
	}

	/**
	 * 图标默认url
	 * @param iconDefaultUrl 图标默认url
	 */
	public void setIconDefaultUrl(String iconDefaultUrl) {
		this.iconDefaultUrl = iconDefaultUrl;
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
	 * 是否显示平台logo 1：显示 0：不显示
	 * @return 是否显示平台logo 1：显示 0：不显示
	 */
	public Integer getShowPlatformLogo() {
		return showPlatformLogo;
	}

	/**
	 * 是否显示平台logo 1：显示 0：不显示
	 * @param showPlatformLogo 是否显示平台logo 1：显示 0：不显示
	 */
	public void setShowPlatformLogo(Integer showPlatformLogo) {
		this.showPlatformLogo = showPlatformLogo;
	}	
	/**
	 * 是否显示客户Logo 1:显示 0:不显示
	 * @return 是否显示客户Logo 1:显示 0:不显示
	 */
	public Integer getShowClientLogo() {
		return showClientLogo;
	}

	/**
	 * 是否显示客户Logo 1:显示 0:不显示
	 * @param showClientLogo 是否显示客户Logo 1:显示 0:不显示
	 */
	public void setShowClientLogo(Integer showClientLogo) {
		this.showClientLogo = showClientLogo;
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
	 * 客户端类型
	 * @return 客户端类型
	 */
	public String getClientType() {
		return clientType;
	}

	/**
	 * 客户端类型
	 * @param clientType 客户端类型
	 */
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}	
		
	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	/**
	 * tab页类型 1：首页
	 * @return tab页类型 1：首页
	 */
	public String getType() {
		return type;
	}

	/**
	 * tab页类型 1：首页
	 * @param type tab页类型 1：首页
	 */
	public void setType(String type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIconOnclickUrl() {
		return iconOnclickUrl;
	}

	public void setIconOnclickUrl(String iconOnclickUrl) {
		this.iconOnclickUrl = iconOnclickUrl;
	}

	public String getIconOnclickDefaultUrl() {
		return iconOnclickDefaultUrl;
	}

	public void setIconOnclickDefaultUrl(String iconOnclickDefaultUrl) {
		this.iconOnclickDefaultUrl = iconOnclickDefaultUrl;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getPageTabUrl() {
		return pageTabUrl;
	}

	public void setPageTabUrl(String pageTabUrl) {
		this.pageTabUrl = pageTabUrl;
	}
}
	