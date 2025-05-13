package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-12-13 17:01:38
 */
public class CmsInstPO {


	private Long id;

	/**
	 * 页面ID
	 */
	private Long cmsPageId;	

	/**
	 * 组件ID
	 */
	private Long cmsElementId;	

	/**
	 * 实例名称
	 */
	private String name;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 实例是否配置完成 0-否 1-是
	 */
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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
	 * 组件ID
	 * @return 组件ID
	 */
	public Long getCmsElementId() {
		return cmsElementId;
	}

	/**
	 * 组件ID
	 * @param cmsElementId 组件ID
	 */
	public void setCmsElementId(Long cmsElementId) {
		this.cmsElementId = cmsElementId;
	}

	/**
	 * 实例名称
	 * @return 实例名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 实例名称
	 * @param name 实例名称
	 */
	public void setName(String name) {
		this.name = name;
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
	