package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantProdVideoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 
	 */
	private Long merchantProdId;	

	/**
	 * 
	 */
	private Long merchantVideoId;	

	/**
	 * 
	 */
	private String name;	

	/**
	 * 
	 */
	private Integer type;	

	/**
	 * 
	 */
	private String thumbnailUrl;	

	/**
	 * 
	 */
	private String videoUrl;	

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
	public Long getMerchantProdId() {
		return merchantProdId;
	}

	/**
	 * 
	 * @param merchantProdId 
	 */
	public void setMerchantProdId(Long merchantProdId) {
		this.merchantProdId = merchantProdId;
	}
	/**
	 * 
	 * @return 
	 */
	public Long getMerchantVideoId() {
		return merchantVideoId;
	}

	/**
	 * 
	 * @param merchantVideoId 
	 */
	public void setMerchantVideoId(Long merchantVideoId) {
		this.merchantVideoId = merchantVideoId;
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
	 * 
	 * @return 
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 
	 * @param type 
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 
	 * @return 
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	/**
	 * 
	 * @param thumbnailUrl 
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	/**
	 * 
	 * @return 
	 */
	public String getVideoUrl() {
		return videoUrl;
	}

	/**
	 * 
	 * @param videoUrl 
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
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
	