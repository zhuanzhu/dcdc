package com.egeo.components.promotion.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 16:42:11
 */
public class PromotionSkuPO {


	private Long id;

	/**
	 * 促销活动id
	 */
	private Long promotionId;	

	/**
	 * 价格同步状态：默认0 成功， 1 失败
	 */
	private Integer synStatus;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 商品id
	 */
	private Long mpId;	

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
	 * 促销活动id
	 * @return 促销活动id
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * 促销活动id
	 * @param promotionId 促销活动id
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	/**
	 * 价格同步状态：默认0 成功， 1 失败
	 * @return 价格同步状态：默认0 成功， 1 失败
	 */
	public Integer getSynStatus() {
		return synStatus;
	}

	/**
	 * 价格同步状态：默认0 成功， 1 失败
	 * @param synStatus 价格同步状态：默认0 成功， 1 失败
	 */
	public void setSynStatus(Integer synStatus) {
		this.synStatus = synStatus;
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
	 * 商品id
	 * @return 商品id
	 */
	public Long getMpId() {
		return mpId;
	}

	/**
	 * 商品id
	 * @param mpId 商品id
	 */
	public void setMpId(Long mpId) {
		this.mpId = mpId;
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
	