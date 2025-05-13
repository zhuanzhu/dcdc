package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:45
 */
public class BannerInstDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 轮播图id
	 */
	private Long bannerId;	

	/**
	 * 实例id
	 */
	private Long instId;	

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
	 * 轮播图id
	 * @return 轮播图id
	 */
	public Long getBannerId() {
		return bannerId;
	}

	/**
	 * 轮播图id
	 * @param bannerId 轮播图id
	 */
	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}
	/**
	 * 实例id
	 * @return 实例id
	 */
	public Long getInstId() {
		return instId;
	}

	/**
	 * 实例id
	 * @param instId 实例id
	 */
	public void setInstId(Long instId) {
		this.instId = instId;
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
	