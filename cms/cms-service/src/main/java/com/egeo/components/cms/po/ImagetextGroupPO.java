package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:48
 */
public class ImagetextGroupPO {


	private Long id;

	/**
	 * 图文组件类型 0:一张图 1:三张图
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
	
	/**
	 * 组件组类型 0:普通(默认) 1:elementList
	 */
	private Integer groupType;

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
	 * 图文组件类型 0:一张图 1:三张图
	 * @return 图文组件类型 0:一张图 1:三张图
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 图文组件类型 0:一张图 1:三张图
	 * @param type 图文组件类型 0:一张图 1:三张图
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

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
}
	