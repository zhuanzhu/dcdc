package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-09-12 13:10:48
 */
public class StoreMenuNodeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */
	private Long parentId;
	/**
	 * 门店菜单类目树id
	 */
	private Long storeMenuTreeId;
	/**
	 * 门店id
	 */
	private Long storeId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 平台id
	 */
	private Long platformId;
	/**
	 * 排序
	 */
	private Integer sortValue;
	/**
	 * 说明
	 */
	private String description;
	/**
	 * 是否全部门店菜单 : 0否1是
	 */
	private Integer isAll;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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
	 * 门店菜单类目树id
	 * @return 门店菜单类目树id
	 */
	public Long getStoreMenuTreeId() {
		return storeMenuTreeId;
	}

	/**
	 * 门店菜单类目树id
	 * @param storeMenuTreeId 门店菜单类目树id
	 */
	public void setStoreMenuTreeId(Long storeMenuTreeId) {
		this.storeMenuTreeId = storeMenuTreeId;
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
	 * 排序
	 * @return 排序
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序
	 * @param sortValue 排序
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}	
	/**
	 * 说明
	 * @return 说明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 说明
	 * @param description 说明
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
	/**
	 * 是否全部门店菜单 : 0否1是
	 * @return 是否全部门店菜单 : 0否1是
	 */
	public Integer getIsAll() {
		return isAll;
	}

	/**
	 * 是否全部门店菜单 : 0否1是
	 * @param isAll 是否全部门店菜单 : 0否1是
	 */
	public void setIsAll(Integer isAll) {
		this.isAll = isAll;
	}	
}
	