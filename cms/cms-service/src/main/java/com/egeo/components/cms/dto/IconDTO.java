package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:46
 */
public class IconDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 所属icon组id
	 */
	private Long groupId;	

	/**
	 * 
	 */
	private String url;	

	/**
	 * icon名
	 */
	private String name;	

	/**
	 * 可跳转链接id
	 */
	private Long linkableId;	

	/**
	 * 排序
	 */
	private Integer sort;	
	
	/**
	 * 简介
	 */
	private String summary;

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

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
	 * 所属icon组id
	 * @return 所属icon组id
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * 所属icon组id
	 * @param groupId 所属icon组id
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	/**
	 * 
	 * @return 
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url 
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * icon名
	 * @return icon名
	 */
	public String getName() {
		return name;
	}

	/**
	 * icon名
	 * @param name icon名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 可跳转链接id
	 * @return 可跳转链接id
	 */
	public Long getLinkableId() {
		return linkableId;
	}

	/**
	 * 可跳转链接id
	 * @param linkableId 可跳转链接id
	 */
	public void setLinkableId(Long linkableId) {
		this.linkableId = linkableId;
	}
	/**
	 * 排序
	 * @return 排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 排序
	 * @param sort 排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
	