package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-06 13:26:51
 */
public class StandardProductUnitPicturePO {


	private Long id;

	/**
	 * spu草稿Id
	 */
	private Long standardProductUnitId;	

	/**
	 * 图片表ID
	 */
	private Long pictureId;	

	/**
	 * 类型：1、列表图片 2、轮播图片
	 */
	private Integer type;	

	/**
	 * 排序号
	 */
	private Integer sortValue;	

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
	 * spu草稿Id
	 * @return spu草稿Id
	 */
	public Long getStandardProductUnitId() {
		return standardProductUnitId;
	}

	/**
	 * spu草稿Id
	 * @param standardProductUnitId spu草稿Id
	 */
	public void setStandardProductUnitId(Long standardProductUnitId) {
		this.standardProductUnitId = standardProductUnitId;
	}

	/**
	 * 图片表ID
	 * @return 图片表ID
	 */
	public Long getPictureId() {
		return pictureId;
	}

	/**
	 * 图片表ID
	 * @param pictureId 图片表ID
	 */
	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
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
	 * 排序号
	 * @return 排序号
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序号
	 * @param sortValue 排序号
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
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
	