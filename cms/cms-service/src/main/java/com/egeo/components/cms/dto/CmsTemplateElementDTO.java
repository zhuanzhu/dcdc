package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-12-13 20:36:02
 */
public class CmsTemplateElementDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 模板ID
	 */
	private Long templateId;	

	/**
	 * 组件ID
	 */
	private Long elementId;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 排序
	 */
	private Integer sort;
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
	 * 组件ID
	 * @return 组件ID
	 */
	public Long getElementId() {
		return elementId;
	}

	/**
	 * 组件ID
	 * @param elementId 组件ID
	 */
	public void setElementId(Long elementId) {
		this.elementId = elementId;
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
	 * 修改时间
	 * @return 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间
	 * @param updateTime 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	