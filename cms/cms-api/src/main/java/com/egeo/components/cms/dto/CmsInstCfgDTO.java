package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-12-14 17:57:13
 */
public class CmsInstCfgDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 实例ID
	 */
	private Long cmsInstId;	

	/**
	 * 配置项枚举属性值
	 */
	private String cmsCfgValueCode;	

	/**
	 * 配置项文本属性值
	 */
	private String instTextValue;	

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
	
	private Long cmsElementId;
	
	private String ckgKeyCode;

	private String ckgKeyDescription;
	
	private Integer ckgKeyType;

	private Integer sort;
	
	private Integer instStatus;
	
	private Long parentId;
	
	private Long instId;
	
	public Long getInstId() {
		return instId;
	}

	public void setInstId(Long instId) {
		this.instId = instId;
	}

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
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getCmsElementId() {
		return cmsElementId;
	}

	public void setCmsElementId(Long cmsElementId) {
		this.cmsElementId = cmsElementId;
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
	 * 实例ID
	 * @return 实例ID
	 */
	public Long getCmsInstId() {
		return cmsInstId;
	}

	/**
	 * 实例ID
	 * @param cmsInstId 实例ID
	 */
	public void setCmsInstId(Long cmsInstId) {
		this.cmsInstId = cmsInstId;
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
	public String getInstTextValue() {
		return instTextValue;
	}

	/**
	 * 配置项文本属性值
	 * @param instTextValue 配置项文本属性值
	 */
	public void setInstTextValue(String instTextValue) {
		this.instTextValue = instTextValue;
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

	public Integer getInstStatus() {
		return instStatus;
	}

	public void setInstStatus(Integer instStatus) {
		this.instStatus = instStatus;
	}
	
}
	