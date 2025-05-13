package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-05 19:55:46
 */
public class StandardProductUnitAttValueRecordVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */
	private Long standardProductUnitAttNameRecordId;
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
	 * 产品图片路径
	 */
	private String pictureUrl;
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
	public Long getStandardProductUnitAttNameRecordId() {
		return standardProductUnitAttNameRecordId;
	}

	/**
	 * 
	 * @param standardProductUnitAttNameRecordId 
	 */
	public void setStandardProductUnitAttNameRecordId(Long standardProductUnitAttNameRecordId) {
		this.standardProductUnitAttNameRecordId = standardProductUnitAttNameRecordId;
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
	 * 产品图片路径
	 * @return 产品图片路径
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * 产品图片路径
	 * @param pictureUrl 产品图片路径
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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
	