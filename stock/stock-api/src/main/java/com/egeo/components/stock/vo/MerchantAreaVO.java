package com.egeo.components.stock.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 11:46:24
 */
public class MerchantAreaVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 商家id
	 */

	private Long merchantId;		 
	/**
	 * 省份ID
	 */

	private Long provinceId;		 
	/**
	 * 城市ID	
	 */

	private Long cityId;		 
	/**
	 * 区/县 ID
	 */

	private Long countyId;		 
	/**
	 * 四级区域ID
	 */

	private Long areaId;		 
	/**
	 * 覆盖级别
	 */

	private Integer coverLevel;		 
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
	 * 商家id
	 * @return 商家id
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家id
	 * @param merchantId 商家id
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}	
	/**
	 * 省份ID
	 * @return 省份ID
	 */
	public Long getProvinceId() {
		return provinceId;
	}

	/**
	 * 省份ID
	 * @param provinceId 省份ID
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}	
	/**
	 * 城市ID	
	 * @return 城市ID	
	 */
	public Long getCityId() {
		return cityId;
	}

	/**
	 * 城市ID	
	 * @param cityId 城市ID	
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}	
	/**
	 * 区/县 ID
	 * @return 区/县 ID
	 */
	public Long getCountyId() {
		return countyId;
	}

	/**
	 * 区/县 ID
	 * @param countyId 区/县 ID
	 */
	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}	
	/**
	 * 四级区域ID
	 * @return 四级区域ID
	 */
	public Long getAreaId() {
		return areaId;
	}

	/**
	 * 四级区域ID
	 * @param areaId 四级区域ID
	 */
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}	
	/**
	 * 覆盖级别
	 * @return 覆盖级别
	 */
	public Integer getCoverLevel() {
		return coverLevel;
	}

	/**
	 * 覆盖级别
	 * @param coverLevel 覆盖级别
	 */
	public void setCoverLevel(Integer coverLevel) {
		this.coverLevel = coverLevel;
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
	