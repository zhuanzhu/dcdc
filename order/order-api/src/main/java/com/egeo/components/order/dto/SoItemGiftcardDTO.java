package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoItemGiftcardDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 父订单编号
	 */
	private String parentOrderCode;	

	/**
	 * 礼金卡id
	 */
	private Long giftcardId;	

	/**
	 * 商品ID
	 */
	private Long mpId;	

	/**
	 * 商品购买数量
	 */
	private Integer productItemNum;	

	/**
	 * 分摊到此商品礼金卡的金额
	 */
	private BigDecimal giftcardAmount;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

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
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 订单编号
	 * @return 订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单编号
	 * @param orderCode 订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * 父订单编号
	 * @return 父订单编号
	 */
	public String getParentOrderCode() {
		return parentOrderCode;
	}

	/**
	 * 父订单编号
	 * @param parentOrderCode 父订单编号
	 */
	public void setParentOrderCode(String parentOrderCode) {
		this.parentOrderCode = parentOrderCode;
	}
	/**
	 * 礼金卡id
	 * @return 礼金卡id
	 */
	public Long getGiftcardId() {
		return giftcardId;
	}

	/**
	 * 礼金卡id
	 * @param giftcardId 礼金卡id
	 */
	public void setGiftcardId(Long giftcardId) {
		this.giftcardId = giftcardId;
	}
	/**
	 * 商品ID
	 * @return 商品ID
	 */
	public Long getMpId() {
		return mpId;
	}

	/**
	 * 商品ID
	 * @param mpId 商品ID
	 */
	public void setMpId(Long mpId) {
		this.mpId = mpId;
	}
	/**
	 * 商品购买数量
	 * @return 商品购买数量
	 */
	public Integer getProductItemNum() {
		return productItemNum;
	}

	/**
	 * 商品购买数量
	 * @param productItemNum 商品购买数量
	 */
	public void setProductItemNum(Integer productItemNum) {
		this.productItemNum = productItemNum;
	}
	/**
	 * 分摊到此商品礼金卡的金额
	 * @return 分摊到此商品礼金卡的金额
	 */
	public BigDecimal getGiftcardAmount() {
		return giftcardAmount;
	}

	/**
	 * 分摊到此商品礼金卡的金额
	 * @param giftcardAmount 分摊到此商品礼金卡的金额
	 */
	public void setGiftcardAmount(BigDecimal giftcardAmount) {
		this.giftcardAmount = giftcardAmount;
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
	