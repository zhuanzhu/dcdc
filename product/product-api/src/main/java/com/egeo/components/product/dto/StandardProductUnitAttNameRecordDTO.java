package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-05 19:55:45
 */
public class StandardProductUnitAttNameRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 
	 */
	private Long parentId;	

	/**
	 * spu草稿记录id
	 */
	private Long standardProductUnitRecordId;	

	/**
	 * 
	 */
	private Long attNameId;	

	/**
	 * 
	 */
	private Integer sortValue;	

	/**
	 * 产品属性类型：1、属性 、规格
	 */
	private Integer type;	

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
	 * spu草稿记录id
	 * @return spu草稿记录id
	 */
	public Long getStandardProductUnitRecordId() {
		return standardProductUnitRecordId;
	}

	/**
	 * spu草稿记录id
	 * @param standardProductUnitRecordId spu草稿记录id
	 */
	public void setStandardProductUnitRecordId(Long standardProductUnitRecordId) {
		this.standardProductUnitRecordId = standardProductUnitRecordId;
	}
	/**
	 * 
	 * @return 
	 */
	public Long getAttNameId() {
		return attNameId;
	}

	/**
	 * 
	 * @param attNameId 
	 */
	public void setAttNameId(Long attNameId) {
		this.attNameId = attNameId;
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
	 * 产品属性类型：1、属性 、规格
	 * @return 产品属性类型：1、属性 、规格
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 产品属性类型：1、属性 、规格
	 * @param type 产品属性类型：1、属性 、规格
	 */
	public void setType(Integer type) {
		this.type = type;
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
	