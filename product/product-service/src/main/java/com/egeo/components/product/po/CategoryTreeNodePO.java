package com.egeo.components.product.po;


import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryTreeNodePO {


	private Long id;

	/**
	 * 
	 */
	private Long parentId;	

	/**
	 * 
	 */
	private String pids;	

	/**
	 * 
	 */
	private Long categoryTreeId;	

	/**
	 * 
	 */
	private Long categoryId;	

	/**
	 * 列表排序
	 */
	private Integer listSort;	

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
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 
	 * @param parentId 
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 
	 * @return 
	 */
	public String getPids() {
		return pids;
	}

	/**
	 * 
	 * @param pids 
	 */
	public void setPids(String pids) {
		this.pids = pids;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getCategoryTreeId() {
		return categoryTreeId;
	}

	/**
	 * 
	 * @param categoryTreeId 
	 */
	public void setCategoryTreeId(Long categoryTreeId) {
		this.categoryTreeId = categoryTreeId;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * 
	 * @param categoryId 
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 列表排序
	 * @return 列表排序
	 */
	public Integer getListSort() {
		return listSort;
	}

	/**
	 * 列表排序
	 * @param listSort 列表排序
	 */
	public void setListSort(Integer listSort) {
		this.listSort = listSort;
	}

	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
	