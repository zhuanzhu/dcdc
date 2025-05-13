package com.egeo.components.stock.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-09-13 05:39:58
 */
public class StorePuWarehouseStockVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 门店id
	 */
	private Long storeId;
	/**
	 * su商品id
	 */
	private Long standardUnitId;
	/**
	 * 商家id
	 */
	private Long merchantId;
	/**
	 * 仓库ID
	 */
	private Long warehouseId;
	/**
	 * 真实库存
	 */
	private Long realStockNum;
	/**
	 * 真实冻结库存
	 */
	private Long realFrozenStockNum;
	/**
	 * 商品的仓库优先级
	 */
	private Integer pmWarehousePriority;
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
	 * 门店puId
	 */
	private Long storeProductUnitId;

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
	 * 门店id
	 * @return 门店id
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 门店id
	 * @param storeId 门店id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}	
	/**
	 * su商品id
	 * @return su商品id
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * su商品id
	 * @param standardUnitId su商品id
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
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
	 * 真实库存
	 * @return 真实库存
	 */
	public Long getRealStockNum() {
		return realStockNum;
	}

	/**
	 * 真实库存
	 * @param realStockNum 真实库存
	 */
	public void setRealStockNum(Long realStockNum) {
		this.realStockNum = realStockNum;
	}	
	/**
	 * 真实冻结库存
	 * @return 真实冻结库存
	 */
	public Long getRealFrozenStockNum() {
		return realFrozenStockNum;
	}

	/**
	 * 真实冻结库存
	 * @param realFrozenStockNum 真实冻结库存
	 */
	public void setRealFrozenStockNum(Long realFrozenStockNum) {
		this.realFrozenStockNum = realFrozenStockNum;
	}	
	/**
	 * 商品的仓库优先级
	 * @return 商品的仓库优先级
	 */
	public Integer getPmWarehousePriority() {
		return pmWarehousePriority;
	}

	/**
	 * 商品的仓库优先级
	 * @param pmWarehousePriority 商品的仓库优先级
	 */
	public void setPmWarehousePriority(Integer pmWarehousePriority) {
		this.pmWarehousePriority = pmWarehousePriority;
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
	 * 门店puId
	 * @return 门店puId
	 */
	public Long getStoreProductUnitId() {
		return storeProductUnitId;
	}

	/**
	 * 门店puId
	 * @param storeProductUnitId 门店puId
	 */
	public void setStoreProductUnitId(Long storeProductUnitId) {
		this.storeProductUnitId = storeProductUnitId;
	}	
}
	