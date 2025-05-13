package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryTreeNodeRelationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 
	 */
	private Long backTreeNodeId;	

	/**
	 * 
	 */
	private Long frontTreeNodeId;	

	/**
	 * 
	 */
	private Integer sortValue;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 属性聚合类型:1:全部聚合,2:部分聚合
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
	public Long getBackTreeNodeId() {
		return backTreeNodeId;
	}

	/**
	 * 
	 * @param backTreeNodeId 
	 */
	public void setBackTreeNodeId(Long backTreeNodeId) {
		this.backTreeNodeId = backTreeNodeId;
	}
	/**
	 * 
	 * @return 
	 */
	public Long getFrontTreeNodeId() {
		return frontTreeNodeId;
	}

	/**
	 * 
	 * @param frontTreeNodeId 
	 */
	public void setFrontTreeNodeId(Long frontTreeNodeId) {
		this.frontTreeNodeId = frontTreeNodeId;
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
	 * 属性聚合类型:1:全部聚合,2:部分聚合
	 * @return 属性聚合类型:1:全部聚合,2:部分聚合
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 属性聚合类型:1:全部聚合,2:部分聚合
	 * @param type 属性聚合类型:1:全部聚合,2:部分聚合
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
}
	