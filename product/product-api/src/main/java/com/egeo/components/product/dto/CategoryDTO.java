package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 类目编号
	 */
	private String serialNumber;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private String categoryLable;

	/**
	 * 
	 */
	private String description;

	/**
	 * 平台id
	 */
	private Long platformId;

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;

	private List<CategoryDTO> lists;

	private Long parentId;
	
	private Long categoryBannerId;
	/**
	 * 类目节点id
	 */
	private Long categoryTreeNodeId;

	public Long getCategoryTreeNodeId() {
		return categoryTreeNodeId;
	}

	public void setCategoryTreeNodeId(Long categoryTreeNodeId) {
		this.categoryTreeNodeId = categoryTreeNodeId;
	}

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
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getCategoryLable() {
		return categoryLable;
	}

	/**
	 * 
	 * @param categoryLable
	 */
	public void setCategoryLable(String categoryLable) {
		this.categoryLable = categoryLable;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 平台id
	 * 
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * 
	 * @param platformId
	 *            平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * 
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * 
	 * @param createTime
	 *            创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * 
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * 
	 * @param updateTime
	 *            修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<CategoryDTO> getLists() {
		return lists;
	}

	public void setLists(List<CategoryDTO> lists) {
		this.lists = lists;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getCategoryBannerId() {
		return categoryBannerId;
	}

	public void setCategoryBannerId(Long categoryBannerId) {
		this.categoryBannerId = categoryBannerId;
	}

}
