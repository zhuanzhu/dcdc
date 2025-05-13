package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:26
 */
public class PictureDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * su草稿图片关系id
	 */
	private Long merchantProdPictureId;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private String url;
	/**
	 * 轮播图url
	 */
	private List<PictureDTO> styleImage;

	/**
	 * 平台id
	 */
	private Long platformId;

	/**
	 * 排序
	 */
	private Integer sortValue;

	/**
	 * 类型：1、列表图片 2、轮播图片 3、web端轮播图
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
	 * 商家图片关系id
	 */
	private Long merchantPictureId;

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
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 平台id
	 * 
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * 
	 * @param platformId
	 *            平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	/**
	 * 排序
	 * 
	 * @return 排序
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序
	 * 
	 * @param sortValue
	 *            排序
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
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

	public List<PictureDTO> getStyleImage() {
		return styleImage;
	}

	public void setStyleImage(List<PictureDTO> styleImage) {
		this.styleImage = styleImage;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * 
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * 
	 * @param createTime
	 *            创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * 
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * 
	 * @param updateTime
	 *            修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getMerchantProdPictureId() {
		return merchantProdPictureId;
	}

	public void setMerchantProdPictureId(Long merchantProdPictureId) {
		this.merchantProdPictureId = merchantProdPictureId;
	}

	public Long getMerchantPictureId() {
		return merchantPictureId;
	}

	public void setMerchantPictureId(Long merchantPictureId) {
		this.merchantPictureId = merchantPictureId;
	}
	
	
}
