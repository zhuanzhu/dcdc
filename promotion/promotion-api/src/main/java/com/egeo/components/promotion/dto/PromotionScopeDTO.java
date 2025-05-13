package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 16:42:11
 */
public class PromotionScopeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 商品id
	 */
	private Long mpId;	

	/**
	 * 促销活动id
	 */
	private Long promotionId;	

	/**
	 * 选品分组
	 */
	private Integer scopeGroup;	

	/**
	 * 选品范围 1,商品 2，类目 3，品牌
	 */
	private Integer scopeType;	

	/**
	 * 
	 */
	private Long categoryid;	

	/**
	 * 
	 */
	private Long brandid;	

	/**
	 * 数据选品是否解析完 0 否 1 是
	 */
	private Integer handlerStatus;	

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
	 * 选品分组
	 * @return 选品分组
	 */
	public Integer getScopeGroup() {
		return scopeGroup;
	}

	/**
	 * 选品分组
	 * @param scopeGroup 选品分组
	 */
	public void setScopeGroup(Integer scopeGroup) {
		this.scopeGroup = scopeGroup;
	}
	/**
	 * 选品范围 1,商品 2，类目 3，品牌
	 * @return 选品范围 1,商品 2，类目 3，品牌
	 */
	public Integer getScopeType() {
		return scopeType;
	}

	/**
	 * 选品范围 1,商品 2，类目 3，品牌
	 * @param scopeType 选品范围 1,商品 2，类目 3，品牌
	 */
	public void setScopeType(Integer scopeType) {
		this.scopeType = scopeType;
	}
	/**
	 * 
	 * @return 
	 */
	public Long getCategoryid() {
		return categoryid;
	}

	/**
	 * 
	 * @param categoryid 
	 */
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	/**
	 * 
	 * @return 
	 */
	public Long getBrandid() {
		return brandid;
	}

	/**
	 * 
	 * @param brandid 
	 */
	public void setBrandid(Long brandid) {
		this.brandid = brandid;
	}
	/**
	 * 数据选品是否解析完 0 否 1 是
	 * @return 数据选品是否解析完 0 否 1 是
	 */
	public Integer getHandlerStatus() {
		return handlerStatus;
	}

	/**
	 * 数据选品是否解析完 0 否 1 是
	 * @param handlerStatus 数据选品是否解析完 0 否 1 是
	 */
	public void setHandlerStatus(Integer handlerStatus) {
		this.handlerStatus = handlerStatus;
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
	