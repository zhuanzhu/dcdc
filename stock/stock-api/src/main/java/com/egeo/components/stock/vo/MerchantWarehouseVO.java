package com.egeo.components.stock.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-19 16:20:35
 */
public class MerchantWarehouseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 商家id
	 */

	private Long merchantId;		 
	/**
	 * 子公司ID
	 */

	private Long subCompanyId;		 
	/**
	 * 仓库ID
	 */

	private Long warehouseId;		 
	/**
	 * 是否该商家的默认仓库
	 */

	private Integer isDefaultWarehouse;		 
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
	 * 子公司ID
	 * @return 子公司ID
	 */
	public Long getSubCompanyId() {
		return subCompanyId;
	}

	/**
	 * 子公司ID
	 * @param subCompanyId 子公司ID
	 */
	public void setSubCompanyId(Long subCompanyId) {
		this.subCompanyId = subCompanyId;
	}	
	/**
	 * 仓库ID
	 * @return 仓库ID
	 */
	public Long getWarehouseId() {
		return warehouseId;
	}

	/**
	 * 仓库ID
	 * @param warehouseId 仓库ID
	 */
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}	
	/**
	 * 是否该商家的默认仓库
	 * @return 是否该商家的默认仓库
	 */
	public Integer getIsDefaultWarehouse() {
		return isDefaultWarehouse;
	}

	/**
	 * 是否该商家的默认仓库
	 * @param isDefaultWarehouse 是否该商家的默认仓库
	 */
	public void setIsDefaultWarehouse(Integer isDefaultWarehouse) {
		this.isDefaultWarehouse = isDefaultWarehouse;
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
	