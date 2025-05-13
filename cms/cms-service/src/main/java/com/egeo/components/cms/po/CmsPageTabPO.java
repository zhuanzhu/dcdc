package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author mingqiang.luo
 * @date 2019-01-08 20:41:56
 */
public class CmsPageTabPO {


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
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * tab页类型 1：首页
	 */
	private String type;	
	
	private Integer sort;
	
	private String iconOnclickUrl;
	
	private String iconOnclickDefaultUrl;

	/**
	 * 页面tab调整目标url
	 */
	private String pageTabUrl;

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

	public String getPageTabUrl() {
		return pageTabUrl;
	}

	public void setPageTabUrl(String pageTabUrl) {
		this.pageTabUrl = pageTabUrl;
	}
}
	