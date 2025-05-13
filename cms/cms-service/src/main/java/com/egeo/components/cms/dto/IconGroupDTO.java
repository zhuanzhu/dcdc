package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:46
 */
public class IconGroupDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 数量
	 */
	private Integer count;	

	/**
	 * 组类型 0:商城无标题 1:商城有标题 2:应用
	 */
	private Integer type;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	private Long instId;
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getInstId() {
		return instId;
	}

	public void setInstId(Long instId) {
		this.instId = instId;
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
	 * 数量
	 * @return 数量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 数量
	 * @param count 数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 组类型 0:商城无标题 1:商城有标题 2:应用
	 * @return 组类型 0:商城无标题 1:商城有标题 2:应用
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 组类型 0:商城无标题 1:商城有标题 2:应用
	 * @param type 组类型 0:商城无标题 1:商城有标题 2:应用
	 */
	public void setType(Integer type) {
		this.type = type;
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
	