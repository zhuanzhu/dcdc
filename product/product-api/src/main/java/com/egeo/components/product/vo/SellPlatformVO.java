package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author paul
 * @date 2018-09-13 18:06:13
 */
public class SellPlatformVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 平台名称
	 */
	private String name;
	/**
	 * 图片路径
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
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Integer sortValue;
	/**
	 * 
	 */
	private String remark;

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
	 * 平台名称
	 * @return 平台名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 平台名称
	 * @param name 平台名称
	 */
	public void setName(String name) {
		this.name = name;
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
	 * 
	 * @return 
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status 
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * 
	 * @return 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 
	 * @param remark 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}	
}
	