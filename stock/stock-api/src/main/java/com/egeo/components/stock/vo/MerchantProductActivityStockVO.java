package com.egeo.components.stock.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 09:12:15
 */
public class MerchantProductActivityStockVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 活动ID
	 */

	private Long activityId;		 
	/**
	 * 活动名称
	 */

	private String activityName;		 
	/**
	 * 活动类型
	 */

	private Integer activityType;		 
	/**
	 * 活动库存上限数
	 */

	private Long thresholdNum;		 
	/**
	 * 活动已购买数
	 */

	private Long alreadySaleNum;		 
	/**
	 * 主品活动记录的主键(本表的ID,主子品时的上下级关系)
	 */

	private Long parentId;		 
	/**
	 * 子品数量
	 */

	private Long singleProductNum;		 
	/**
	 * 仓库ID
	 */

	private Long warehouseId;		 
	/**
	 * 活动库存销售数量是否可超过上限阈值(创建活动库存时设置的最大销售数量) 0：限制 1：不限制
	 */

	private Integer stockLimit;		 
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
	 * 活动ID
	 * @return 活动ID
	 */
	public Long getActivityId() {
		return activityId;
	}

	/**
	 * 活动ID
	 * @param activityId 活动ID
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}	
	/**
	 * 活动名称
	 * @return 活动名称
	 */
	public String getActivityName() {
		return activityName;
	}

	/**
	 * 活动名称
	 * @param activityName 活动名称
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}	
	/**
	 * 活动类型
	 * @return 活动类型
	 */
	public Integer getActivityType() {
		return activityType;
	}

	/**
	 * 活动类型
	 * @param activityType 活动类型
	 */
	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}	
	/**
	 * 活动库存上限数
	 * @return 活动库存上限数
	 */
	public Long getThresholdNum() {
		return thresholdNum;
	}

	/**
	 * 活动库存上限数
	 * @param thresholdNum 活动库存上限数
	 */
	public void setThresholdNum(Long thresholdNum) {
		this.thresholdNum = thresholdNum;
	}	
	/**
	 * 活动已购买数
	 * @return 活动已购买数
	 */
	public Long getAlreadySaleNum() {
		return alreadySaleNum;
	}

	/**
	 * 活动已购买数
	 * @param alreadySaleNum 活动已购买数
	 */
	public void setAlreadySaleNum(Long alreadySaleNum) {
		this.alreadySaleNum = alreadySaleNum;
	}	
	/**
	 * 主品活动记录的主键(本表的ID,主子品时的上下级关系)
	 * @return 主品活动记录的主键(本表的ID,主子品时的上下级关系)
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 主品活动记录的主键(本表的ID,主子品时的上下级关系)
	 * @param parentId 主品活动记录的主键(本表的ID,主子品时的上下级关系)
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}	
	/**
	 * 子品数量
	 * @return 子品数量
	 */
	public Long getSingleProductNum() {
		return singleProductNum;
	}

	/**
	 * 子品数量
	 * @param singleProductNum 子品数量
	 */
	public void setSingleProductNum(Long singleProductNum) {
		this.singleProductNum = singleProductNum;
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
	 * 活动库存销售数量是否可超过上限阈值(创建活动库存时设置的最大销售数量) 0：限制 1：不限制
	 * @return 活动库存销售数量是否可超过上限阈值(创建活动库存时设置的最大销售数量) 0：限制 1：不限制
	 */
	public Integer getStockLimit() {
		return stockLimit;
	}

	/**
	 * 活动库存销售数量是否可超过上限阈值(创建活动库存时设置的最大销售数量) 0：限制 1：不限制
	 * @param stockLimit 活动库存销售数量是否可超过上限阈值(创建活动库存时设置的最大销售数量) 0：限制 1：不限制
	 */
	public void setStockLimit(Integer stockLimit) {
		this.stockLimit = stockLimit;
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
	