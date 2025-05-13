package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 16:09:32
 */
public class MerchantProductPromotionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 商品id
	 */
	private Long merchantProductId;	

	/**
	 * 产品id
	 */
	private Long productId;	

	/**
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */
	private Long skuId;	

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 促销ID
	 */
	private Long promotionId;	

	/**
	 * 促销价类型
	 */
	private Integer promotionType;	

	/**
	 * 促销价
	 */
	private BigDecimal promotionPrice;	

	/**
	 * 促销开始时间
	 */
	private Date promotionStartTime;	

	/**
	 * 促销开始时间
	 */
	private Date promotionEndTime;	

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
	 * 商品id
	 * @return 商品id
	 */
	public Long getMerchantProductId() {
		return merchantProductId;
	}

	/**
	 * 商品id
	 * @param merchantProductId 商品id
	 */
	public void setMerchantProductId(Long merchantProductId) {
		this.merchantProductId = merchantProductId;
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
	 * 促销ID
	 * @return 促销ID
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * 促销ID
	 * @param promotionId 促销ID
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}
	/**
	 * 促销价类型
	 * @return 促销价类型
	 */
	public Integer getPromotionType() {
		return promotionType;
	}

	/**
	 * 促销价类型
	 * @param promotionType 促销价类型
	 */
	public void setPromotionType(Integer promotionType) {
		this.promotionType = promotionType;
	}
	/**
	 * 促销价
	 * @return 促销价
	 */
	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	/**
	 * 促销价
	 * @param promotionPrice 促销价
	 */
	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	/**
	 * 促销开始时间
	 * @return 促销开始时间
	 */
	public Date getPromotionStartTime() {
		return promotionStartTime;
	}

	/**
	 * 促销开始时间
	 * @param promotionStartTime 促销开始时间
	 */
	public void setPromotionStartTime(Date promotionStartTime) {
		this.promotionStartTime = promotionStartTime;
	}
	/**
	 * 促销开始时间
	 * @return 促销开始时间
	 */
	public Date getPromotionEndTime() {
		return promotionEndTime;
	}

	/**
	 * 促销开始时间
	 * @param promotionEndTime 促销开始时间
	 */
	public void setPromotionEndTime(Date promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
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
	