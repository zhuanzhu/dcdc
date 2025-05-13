package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryAttValuePO {


	private Long id;

	/**
	 * 
	 */
	private Long categoryAttNameId;	

	/**
	 * 
	 */
	private Long attValueId;	

	/**
	 * 
	 */
	private String attValueCustom;	

	/**
	 * 
	 */
	private Integer sortValue;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * productId
	 */
	private Long productId;	

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
	public Long getCategoryAttNameId() {
		return categoryAttNameId;
	}

	/**
	 * 
	 * @param categoryAttNameId 
	 */
	public void setCategoryAttNameId(Long categoryAttNameId) {
		this.categoryAttNameId = categoryAttNameId;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getAttValueId() {
		return attValueId;
	}

	/**
	 * 
	 * @param attValueId 
	 */
	public void setAttValueId(Long attValueId) {
		this.attValueId = attValueId;
	}

	/**
	 * 
	 * @return 
	 */
	public String getAttValueCustom() {
		return attValueCustom;
	}

	/**
	 * 
	 * @param attValueCustom 
	 */
	public void setAttValueCustom(String attValueCustom) {
		this.attValueCustom = attValueCustom;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 
	 * @param sortValue 
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
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
	/**
	 * 
	 * @return 
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * 
	 * @param productId 
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}}
	