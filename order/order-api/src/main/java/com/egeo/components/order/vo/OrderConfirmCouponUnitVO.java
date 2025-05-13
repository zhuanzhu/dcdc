package com.egeo.components.order.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OrderConfirmCouponUnitVO {

	private BigDecimal orderAmountPaidByFuBi;

	public BigDecimal getOrderAmountPaidByFuBi() {
		return orderAmountPaidByFuBi;
	}

	public void setOrderAmountPaidByFuBi(BigDecimal orderAmountPaidByFuBi) {
		this.orderAmountPaidByFuBi = orderAmountPaidByFuBi;
	}

	private Integer canPay;

	public Integer getCanPay() {
		return canPay;
	}

	public void setCanPay(Integer canPay) {
		this.canPay = canPay;
	}

	private Long id;
	private Integer couponUnitStatus;
	private Long effectStartTime;
	private Long effectEndTime;
	private String title;
	private Integer couponType;
	private Integer discountAmount;
	private Integer triggerAmount;
	private String iconUrl;
	private String detail;
	private Integer goodsType;
	private Long goodsId;
	private Integer jumpType;

	private BigDecimal couponDiscount;
	private BigDecimal storeDiscount;

	/**
	 * 优惠卷折后订单总额
	 */
	private BigDecimal afterDiscountAmount;
	private boolean isSelected;
	
	private String effectTimeRange;
	
	/**
	 * 优惠卷折后订单总额
	 */
	private BigDecimal afterDiscountCashPay;
	
	private BigDecimal totalDeliveryPrice;
	
	private List<Map<String, Object>> deliveryPriceList;
	
	private Long cmsPageId;
	
	private boolean isShowEffectTimeRange;

	public BigDecimal getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(BigDecimal couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public BigDecimal getStoreDiscount() {
		return storeDiscount;
	}

	public void setStoreDiscount(BigDecimal storeDiscount) {
		this.storeDiscount = storeDiscount;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCouponUnitStatus() {
		return couponUnitStatus;
	}
	public void setCouponUnitStatus(Integer couponUnitStatus) {
		this.couponUnitStatus = couponUnitStatus;
	}
	public Long getEffectStartTime() {
		return effectStartTime;
	}
	public void setEffectStartTime(Long effectStartTime) {
		this.effectStartTime = effectStartTime;
	}
	public Long getEffectEndTime() {
		return effectEndTime;
	}
	public void setEffectEndTime(Long effectEndTime) {
		this.effectEndTime = effectEndTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public Integer getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Integer getTriggerAmount() {
		return triggerAmount;
	}
	public void setTriggerAmount(Integer triggerAmount) {
		this.triggerAmount = triggerAmount;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public BigDecimal getAfterDiscountAmount() {
		return afterDiscountAmount;
	}
	public void setAfterDiscountAmount(BigDecimal afterDiscountAmount) {
		this.afterDiscountAmount = afterDiscountAmount;
	}
	public Integer getJumpType() {
		return jumpType;
	}
	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}
	public String getEffectTimeRange() {
		return effectTimeRange;
	}
	public void setEffectTimeRange(String effectTimeRange) {
		this.effectTimeRange = effectTimeRange;
	}
	public BigDecimal getAfterDiscountCashPay() {
		return afterDiscountCashPay;
	}
	public void setAfterDiscountCashPay(BigDecimal afterDiscountCashPay) {
		this.afterDiscountCashPay = afterDiscountCashPay;
	}

	public BigDecimal getTotalDeliveryPrice() {
		return totalDeliveryPrice;
	}

	public void setTotalDeliveryPrice(BigDecimal totalDeliveryPrice) {
		this.totalDeliveryPrice = totalDeliveryPrice;
	}

	public List<Map<String, Object>> getDeliveryPriceList() {
		return deliveryPriceList;
	}

	public void setDeliveryPriceList(List<Map<String, Object>> deliveryPriceList) {
		this.deliveryPriceList = deliveryPriceList;
	}

	public Long getCmsPageId() {
		return cmsPageId;
	}

	public void setCmsPageId(Long cmsPageId) {
		this.cmsPageId = cmsPageId;
	}

	public boolean isIsShowEffectTimeRange() {
		return isShowEffectTimeRange;
	}

	public void setIsShowEffectTimeRange(boolean isShowEffectTimeRange) {
		this.isShowEffectTimeRange = isShowEffectTimeRange;
	}
	
}
