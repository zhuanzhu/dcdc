package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-09-04 14:20:11
 */
public class BannerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * Banner名称
	 */
	private String name;	

	/**
	 * Banner类型：1、商品 2、Banner 3、链接
	 */
	private Integer type;	

	/**
	 * 排序
	 */
	private Integer sortValue;	

	/**
	 * 商品id
	 */
	private Long merchantProdId;	

	/**
	 * Bannerid
	 */
	private Long activityId;	

	/**
	 * 图片路径
	 */
	private String pictureUrl;	

	/**
	 * 链接路径
	 */
	private String path;	

	/**
	 * Banner开始时间
	 */
	private Date startTime;	

	/**
	 * Banner结束时间
	 */
	private Date finishTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	
	/**
	 * 当前时间
	 */
	private Date time;

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
	 * Banner名称
	 * @return Banner名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * Banner名称
	 * @param name Banner名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Banner类型：1、商品 2、Banner 3、链接
	 * @return Banner类型：1、商品 2、Banner 3、链接
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * Banner类型：1、商品 2、Banner 3、链接
	 * @param type Banner类型：1、商品 2、Banner 3、链接
	 */
	public void setType(Integer type) {
		this.type = type;
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
	 * 商品id
	 * @return 商品id
	 */
	public Long getMerchantProdId() {
		return merchantProdId;
	}

	/**
	 * 商品id
	 * @param merchantProdId 商品id
	 */
	public void setMerchantProdId(Long merchantProdId) {
		this.merchantProdId = merchantProdId;
	}
	/**
	 * Bannerid
	 * @return Bannerid
	 */
	public Long getActivityId() {
		return activityId;
	}

	/**
	 * Bannerid
	 * @param activityId Bannerid
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	/**
	 * 图片路径
	 * @return 图片路径
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * 图片路径
	 * @param pictureUrl 图片路径
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	/**
	 * 链接路径
	 * @return 链接路径
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 链接路径
	 * @param path 链接路径
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * Banner开始时间
	 * @return Banner开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Banner开始时间
	 * @param startTime Banner开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * Banner结束时间
	 * @return Banner结束时间
	 */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * Banner结束时间
	 * @param finishTime Banner结束时间
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
	