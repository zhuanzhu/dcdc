package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 16:42:11
 */
public class PromotionScopeRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 促销活动id
	 */
	private Long promotionId;	

	/**
	 * 促销选品分组id
	 */
	private Integer scopeGroupId;	

	/**
	 * 
	 */
	private Integer scopeType;	

	/**
	 * 
	 */
	private BigDecimal promPrice;	

	/**
	 * 是否与其他活动互斥
	 */
	private Integer isMutex;	

	/**
	 * 审核状态 0 未审核 1 审核通过
	 */
	private Integer status;	

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
	 * 促销选品分组id
	 * @return 促销选品分组id
	 */
	public Integer getScopeGroupId() {
		return scopeGroupId;
	}

	/**
	 * 促销选品分组id
	 * @param scopeGroupId 促销选品分组id
	 */
	public void setScopeGroupId(Integer scopeGroupId) {
		this.scopeGroupId = scopeGroupId;
	}
	/**
	 * 
	 * @return 
	 */
	public Integer getScopeType() {
		return scopeType;
	}

	/**
	 * 
	 * @param scopeType 
	 */
	public void setScopeType(Integer scopeType) {
		this.scopeType = scopeType;
	}
	/**
	 * 
	 * @return 
	 */
	public BigDecimal getPromPrice() {
		return promPrice;
	}

	/**
	 * 
	 * @param promPrice 
	 */
	public void setPromPrice(BigDecimal promPrice) {
		this.promPrice = promPrice;
	}
	/**
	 * 是否与其他活动互斥
	 * @return 是否与其他活动互斥
	 */
	public Integer getIsMutex() {
		return isMutex;
	}

	/**
	 * 是否与其他活动互斥
	 * @param isMutex 是否与其他活动互斥
	 */
	public void setIsMutex(Integer isMutex) {
		this.isMutex = isMutex;
	}
	/**
	 * 审核状态 0 未审核 1 审核通过
	 * @return 审核状态 0 未审核 1 审核通过
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 审核状态 0 未审核 1 审核通过
	 * @param status 审核状态 0 未审核 1 审核通过
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	