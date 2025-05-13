package com.egeo.components.stock.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 09:12:15
 */
public class MerchantProductWarehouseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 产品id
	 */

	private Long productId;		 
	/**
	 * 商家id	
	 */

	private Long merchantId;		 
	/**
	 * 仓库id
	 */

	private Long warehouseId;		 
	/**
	 * 默认0否，1是
	 */

	private Integer isMainWarehouse;		 
	/**
	 * 覆盖级别  0.覆盖全国 1.覆盖省份 2:覆盖市 3：覆盖区/县 4:覆盖区域
	 */

	private String remark;		 
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
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */

	private Long skuId;		 

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
	 * 产品id
	 * @return 产品id
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * 产品id
	 * @param productId 产品id
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
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
	 * 仓库id
	 * @return 仓库id
	 */
	public Long getWarehouseId() {
		return warehouseId;
	}

	/**
	 * 仓库id
	 * @param warehouseId 仓库id
	 */
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}	
	/**
	 * 默认0否，1是
	 * @return 默认0否，1是
	 */
	public Integer getIsMainWarehouse() {
		return isMainWarehouse;
	}

	/**
	 * 默认0否，1是
	 * @param isMainWarehouse 默认0否，1是
	 */
	public void setIsMainWarehouse(Integer isMainWarehouse) {
		this.isMainWarehouse = isMainWarehouse;
	}	
	/**
	 * 覆盖级别  0.覆盖全国 1.覆盖省份 2:覆盖市 3：覆盖区/县 4:覆盖区域
	 * @return 覆盖级别  0.覆盖全国 1.覆盖省份 2:覆盖市 3：覆盖区/县 4:覆盖区域
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 覆盖级别  0.覆盖全国 1.覆盖省份 2:覆盖市 3：覆盖区/县 4:覆盖区域
	 * @param remark 覆盖级别  0.覆盖全国 1.覆盖省份 2:覆盖市 3：覆盖区/县 4:覆盖区域
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 * @return 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */
	public Long getSkuId() {
		return skuId;
	}

	/**
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 * @param skuId 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}	
}
	