package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-07 11:18:56
 */
public class StandardUnitPictureRecordPO {


	private Long id;

	/**
	 * 
	 */
	private Long merchantPictureId;	

	/**
	 * 类型：1、列表图片 2、轮播图片
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

	/**
	 * su记录id
	 */
	private Long standardUnitRecordId;	

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
	public Long getMerchantPictureId() {
		return merchantPictureId;
	}

	/**
	 * 
	 * @param merchantPictureId 
	 */
	public void setMerchantPictureId(Long merchantPictureId) {
		this.merchantPictureId = merchantPictureId;
	}

	/**
	 * 类型：1、列表图片 2、轮播图片
	 * @return 类型：1、列表图片 2、轮播图片
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型：1、列表图片 2、轮播图片
	 * @param type 类型：1、列表图片 2、轮播图片
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

	/**
	 * su记录id
	 * @return su记录id
	 */
	public Long getStandardUnitRecordId() {
		return standardUnitRecordId;
	}

	/**
	 * su记录id
	 * @param standardUnitRecordId su记录id
	 */
	public void setStandardUnitRecordId(Long standardUnitRecordId) {
		this.standardUnitRecordId = standardUnitRecordId;
	}
}
	