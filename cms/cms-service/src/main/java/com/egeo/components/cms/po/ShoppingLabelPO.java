package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-08 18:06:59
 */
public class ShoppingLabelPO {


	private Long id;

	/**
	 * 标签名
	 */
	private String name;	

	/**
	 * 标签组id
	 */
	private Long groupId;	

	/**
	 * 排序
	 */
	private Integer sort;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 修改时间
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
	 * 标签名
	 * @return 标签名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 标签名
	 * @param name 标签名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 标签组id
	 * @return 标签组id
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * 标签组id
	 * @param groupId 标签组id
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
	