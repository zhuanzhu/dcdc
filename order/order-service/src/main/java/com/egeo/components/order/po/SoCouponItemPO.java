package com.egeo.components.order.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoCouponItemPO {


	private Long id;

	/**
	 * 优惠券信息id
	 */
	private Long soCouponId;	

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 父订单编号
	 */
	private String parentOrderCode;	

	/**
	 * 优惠券编码
	 */
	private String couponCode;	

	/**
	 * 商品ID
	 */
	private Long mpId;	

	/**
	 * 商品购买数量
	 */
	private Integer productItemNum;	

	/**
	 * 分摊到此商品优惠的金额
	 */
	private BigDecimal couponAmount;	

	/**
	 * 是否有效：1 有效；2无效
	 */
	private Long status;	

	/**
	 * 优惠券ID
	 */
	private Long couponId;	

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
	 * 优惠券信息id
	 * @return 优惠券信息id
	 */
	public Long getSoCouponId() {
		return soCouponId;
	}

	/**
	 * 优惠券信息id
	 * @param soCouponId 优惠券信息id
	 */
	public void setSoCouponId(Long soCouponId) {
		this.soCouponId = soCouponId;
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
	 * 优惠券编码
	 * @return 优惠券编码
	 */
	public String getCouponCode() {
		return couponCode;
	}

	/**
	 * 优惠券编码
	 * @param couponCode 优惠券编码
	 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
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
	 * 分摊到此商品优惠的金额
	 * @return 分摊到此商品优惠的金额
	 */
	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	/**
	 * 分摊到此商品优惠的金额
	 * @param couponAmount 分摊到此商品优惠的金额
	 */
	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	/**
	 * 是否有效：1 有效；2无效
	 * @return 是否有效：1 有效；2无效
	 */
	public Long getStatus() {
		return status;
	}

	/**
	 * 是否有效：1 有效；2无效
	 * @param status 是否有效：1 有效；2无效
	 */
	public void setStatus(Long status) {
		this.status = status;
	}

	/**
	 * 优惠券ID
	 * @return 优惠券ID
	 */
	public Long getCouponId() {
		return couponId;
	}

	/**
	 * 优惠券ID
	 * @param couponId 优惠券ID
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
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
	