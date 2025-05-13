package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author xia
 * @date 2018-09-10 09:50:02
 */
public class StoreTreeNodePO {


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
	private Long storeTreeId;	

	/**
	 * 
	 */
	private Long storeId;	

	/**
	 * 列表排序
	 */
	private Integer listSort;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	public Long getStoreTreeId() {
		return storeTreeId;
	}

	/**
	 * 
	 * @param storeTreeId 
	 */
	public void setStoreTreeId(Long storeTreeId) {
		this.storeTreeId = storeTreeId;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 
	 * @param storeId 
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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
}
	