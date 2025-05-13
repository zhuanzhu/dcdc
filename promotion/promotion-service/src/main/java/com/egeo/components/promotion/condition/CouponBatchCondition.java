package com.egeo.components.promotion.condition;

import com.egeo.components.promotion.po.CouponBatchPO;

import java.math.BigDecimal;

/**
 * 
 * @author wyy
 * @date 2018-06-19 14:19:36
 */
public class CouponBatchCondition extends CouponBatchPO {
	private static final long serialVersionUID = 1L;



	/**
	 * 优惠卷标题或优惠卷分组名称
	 */
	private String title;
	
	private Long couponId;
	//优惠券详情
	private String detail;
	private Integer couponType;
	private BigDecimal discountAmount;
	private BigDecimal triggerAmount;

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getTriggerAmount() {
		return triggerAmount;
	}

	public void setTriggerAmount(BigDecimal triggerAmount) {
		this.triggerAmount = triggerAmount;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

}
	