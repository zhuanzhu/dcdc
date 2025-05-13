package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-12-18 12:27:55
 */
public class CmsPageCfgDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 页面ID
	 */
	private Long cmsPageId;	

	/**
	 * 配置项枚举属性值
	 */
	private String cmsCfgValueCode;	

	/**
	 * 配置项文本属性值
	 */
	private String textValue;	

	/**
	 * 配置项ID
	 */
	private Long cmsCfgKeyId;	

	/**
	 * banner图ID
	 */
	private Long bannerId;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	private String ckgKeyCode;
	
	private String ckgKeyDescription;
	
	private Integer ckgKeyType;
	/**
	 * 上级属性ID
	 */
	private Long parentId;
	
	public String getCkgKeyDescription() {
		return ckgKeyDescription;
	}

	public void setCkgKeyDescription(String ckgKeyDescription) {
		this.ckgKeyDescription = ckgKeyDescription;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getCkgKeyCode() {
		return ckgKeyCode;
	}

	public void setCkgKeyCode(String ckgKeyCode) {
		this.ckgKeyCode = ckgKeyCode;
	}

	public Integer getCkgKeyType() {
		return ckgKeyType;
	}

	public void setCkgKeyType(Integer ckgKeyType) {
		this.ckgKeyType = ckgKeyType;
	}
	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 页面ID
	 * @return 页面ID
	 */
	public Long getCmsPageId() {
		return cmsPageId;
	}

	/**
	 * 页面ID
	 * @param cmsPageId 页面ID
	 */
	public void setCmsPageId(Long cmsPageId) {
		this.cmsPageId = cmsPageId;
	}
	/**
	 * 配置项枚举属性值
	 * @return 配置项枚举属性值
	 */
	public String getCmsCfgValueCode() {
		return cmsCfgValueCode;
	}

	/**
	 * 配置项枚举属性值
	 * @param cmsCfgValueCode 配置项枚举属性值
	 */
	public void setCmsCfgValueCode(String cmsCfgValueCode) {
		this.cmsCfgValueCode = cmsCfgValueCode;
	}
	/**
	 * 配置项文本属性值
	 * @return 配置项文本属性值
	 */
	public String getTextValue() {
		return textValue;
	}

	/**
	 * 配置项文本属性值
	 * @param textValue 配置项文本属性值
	 */
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}
	/**
	 * 配置项ID
	 * @return 配置项ID
	 */
	public Long getCmsCfgKeyId() {
		return cmsCfgKeyId;
	}

	/**
	 * 配置项ID
	 * @param cmsCfgKeyId 配置项ID
	 */
	public void setCmsCfgKeyId(Long cmsCfgKeyId) {
		this.cmsCfgKeyId = cmsCfgKeyId;
	}
	/**
	 * banner图ID
	 * @return banner图ID
	 */
	public Long getBannerId() {
		return bannerId;
	}

	/**
	 * banner图ID
	 * @param bannerId banner图ID
	 */
	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
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
}
	