package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-08-01 17:43:32
 */
public class NavigationBarLabelDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 导航栏id
	 */
	private Long navigationBarId;	

	/**
	 * 导航栏标签id
	 */
	private Long navigationLabelId;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

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
	 * 导航栏id
	 * @return 导航栏id
	 */
	public Long getNavigationBarId() {
		return navigationBarId;
	}

	/**
	 * 导航栏id
	 * @param navigationBarId 导航栏id
	 */
	public void setNavigationBarId(Long navigationBarId) {
		this.navigationBarId = navigationBarId;
	}
	/**
	 * 导航栏标签id
	 * @return 导航栏标签id
	 */
	public Long getNavigationLabelId() {
		return navigationLabelId;
	}

	/**
	 * 导航栏标签id
	 * @param navigationLabelId 导航栏标签id
	 */
	public void setNavigationLabelId(Long navigationLabelId) {
		this.navigationLabelId = navigationLabelId;
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
}
	