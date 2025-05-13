package com.egeo.components.order.vo;

import java.math.BigDecimal;

public class SoItemVIEW {

	/**
	 * 子订单编号
	 */
	private String childCode;
	/**
	 * 商品PU编号
	 */
	private String productUnitSerialNumber;
	/**
	 * 商品PU名称
	 */
	private String puNameAndStandard;
	/**
	 * 购买数量
	 */
	private Integer puCount;
	/**
	 * 商品单价
	 */
	private BigDecimal price;
	/**
	 * 平摊优惠券
	 */
	private BigDecimal promotionAver;
	/**
	 * 平摊门店优惠
	 */
	private BigDecimal storeDiscountAver;
	/**
	 * 平摊运费优惠
	 */
	private BigDecimal deliveryFeeDiscountAver;
	/**
	 * 平摊运费
	 */
	private BigDecimal deliveryFeeAver;
	/**
	 * 结算商品单价
	 */
	private BigDecimal afterDiscountPriceAver;
	/**
	 * 商品原价总额
	 */
	private BigDecimal productAmount;
	/**
	 * 优惠前总额
	 */
	private BigDecimal allAmount;
	/**
	 * 优惠总额
	 */
	private BigDecimal discount;
	/**
	 * 结算总额
	 */
	private BigDecimal amount;
	/**
	 * 是否存在unit库存 0:否  1:是
	 */
	private Integer unitExist;
	/**
	 * 商品加入购物车类型 1 正常品 2 赠品 3 换购品 4 实物券|
	 */
	private Integer cartType;

	/**
	 * 商品税率
	 */
	private String taxRate;

	private Long childId;

	private Long soItemId;

	private Integer refundCount;

	private BigDecimal refundAmount;
	
	public String getChildCode() {
		return childCode;
	}
	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}
	public String getProductUnitSerialNumber() {
		return productUnitSerialNumber;
	}
	public void setProductUnitSerialNumber(String productUnitSerialNumber) {
		this.productUnitSerialNumber = productUnitSerialNumber;
	}
	public String getPuNameAndStandard() {
		return puNameAndStandard;
	}
	public void setPuNameAndStandard(String puNameAndStandard) {
		this.puNameAndStandard = puNameAndStandard;
	}
	public Integer getPuCount() {
		return puCount;
	}
	public void setPuCount(Integer puCount) {
		this.puCount = puCount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getPromotionAver() {
		return promotionAver;
	}
	public void setPromotionAver(BigDecimal promotionAver) {
		this.promotionAver = promotionAver;
	}
	public BigDecimal getStoreDiscountAver() {
		return storeDiscountAver;
	}
	public void setStoreDiscountAver(BigDecimal storeDiscountAver) {
		this.storeDiscountAver = storeDiscountAver;
	}
	public BigDecimal getDeliveryFeeDiscountAver() {
		return deliveryFeeDiscountAver;
	}
	public void setDeliveryFeeDiscountAver(BigDecimal deliveryFeeDiscountAver) {
		this.deliveryFeeDiscountAver = deliveryFeeDiscountAver;
	}
	public BigDecimal getDeliveryFeeAver() {
		return deliveryFeeAver;
	}
	public void setDeliveryFeeAver(BigDecimal deliveryFeeAver) {
		this.deliveryFeeAver = deliveryFeeAver;
	}
	public BigDecimal getAfterDiscountPriceAver() {
		return afterDiscountPriceAver;
	}
	public void setAfterDiscountPriceAver(BigDecimal afterDiscountPriceAver) {
		this.afterDiscountPriceAver = afterDiscountPriceAver;
	}
	public BigDecimal getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	public BigDecimal getAllAmount() {
		return allAmount;
	}
	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getUnitExist() {
		return unitExist;
	}
	public void setUnitExist(Integer unitExist) {
		this.unitExist = unitExist;
	}
	public Integer getCartType() {
		return cartType;
	}
	public void setCartType(Integer cartType) {
		this.cartType = cartType;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public Long getSoItemId() {
		return soItemId;
	}

	public void setSoItemId(Long soItemId) {
		this.soItemId = soItemId;
	}

	public Integer getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Integer refundCount) {
		this.refundCount = refundCount;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
}
