package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-06 09:20:45
 */
public class MerchantProdPictureDTO implements Serializable {
	private static final long serialVersionUID = 1L;

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
	 * 商品id
	 */
	private Long merchantProdId;	
	/**
	 * 图片路径
	 */
	private String pictureUrl;

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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
}
	