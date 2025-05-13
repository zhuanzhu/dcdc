package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:41:08
 */
public class MerchantSeriesProductAttVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 系列商品Id
	 */

	private Long merchantSeriesId;		 
	/**
	 * 商品属性表Id
	 */

	private Long attNameId;		 
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
	 * 系列商品Id
	 * @return 系列商品Id
	 */
	public Long getMerchantSeriesId() {
		return merchantSeriesId;
	}

	/**
	 * 系列商品Id
	 * @param merchantSeriesId 系列商品Id
	 */
	public void setMerchantSeriesId(Long merchantSeriesId) {
		this.merchantSeriesId = merchantSeriesId;
	}	
	/**
	 * 商品属性表Id
	 * @return 商品属性表Id
	 */
	public Long getAttNameId() {
		return attNameId;
	}

	/**
	 * 商品属性表Id
	 * @param attNameId 商品属性表Id
	 */
	public void setAttNameId(Long attNameId) {
		this.attNameId = attNameId;
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
	