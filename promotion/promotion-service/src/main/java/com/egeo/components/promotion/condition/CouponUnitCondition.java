package com.egeo.components.promotion.condition;

import java.math.BigDecimal;
import java.util.Date;

import com.egeo.components.promotion.po.CouponUnitPO;

/**
 * 
 * @author wyy
 * @date 2018-06-19 14:06:21
 */
public class CouponUnitCondition extends CouponUnitPO {
	private static final long serialVersionUID = 1L;

	private String couponCode;
	private String couponBatchCode;

	@Override
	public Long getCouponBatchId() {
		return couponBatchId;
	}

	@Override
	public void setCouponBatchId(Long couponBatchId) {
		this.couponBatchId = couponBatchId;
	}

	private Long couponBatchId;
	private Integer couponType;
	private String title;
	private Integer grantType;
	private Integer getType;

	public Integer getGetType() {
		return getType;
	}

	public void setGetType(Integer getType) {
		this.getType = getType;
	}

	// 客户端拓展信息
	private BigDecimal discountAmount;
	private BigDecimal triggerAmount;
	private String iconUrl;
	private String detail;
	private Date couponBatchEffectStartTime;	
	private Date couponBatchEffectEndTime;	
	private Integer grantCount;
	private Integer isRepeat;
	private Integer jumpType;
	private Integer goodsType;
	private Long goodsId;
	private Integer couponRelType;
	private Long couponRelId;
	private Long companyId;
	private Integer effectDays;
	private Long storeId;
	private Date couponBatchReceiveStartTime;
	private Date couponBatchReceiveEndTime;
	private String couponBatchName;

	public String getCouponBatchName() {
		return couponBatchName;
	}
	public void setCouponBatchName(String couponBatchName) {
		this.couponBatchName = couponBatchName;
	}

	public Date getCouponBatchReceiveStartTime() {
		return couponBatchReceiveStartTime;
	}

	public void setCouponBatchReceiveStartTime(Date couponBatchReceiveStartTime) {
		this.couponBatchReceiveStartTime = couponBatchReceiveStartTime;
	}

	public Date getCouponBatchReceiveEndTime() {
		return couponBatchReceiveEndTime;
	}

	public void setCouponBatchReceiveEndTime(Date couponBatchReceiveEndTime) {
		this.couponBatchReceiveEndTime = couponBatchReceiveEndTime;
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
	public Integer getCouponRelType() {
		return couponRelType;
	}
	public void setCouponRelType(Integer couponRelType) {
		this.couponRelType = couponRelType;
	}
	public Long getCouponRelId() {
		return couponRelId;
	}
	public void setCouponRelId(Long couponRelId) {
		this.couponRelId = couponRelId;
	}
	
	public Date getCouponBatchEffectStartTime() {
		return couponBatchEffectStartTime;
	}
	public void setCouponBatchEffectStartTime(Date couponBatchEffectStartTime) {
		this.couponBatchEffectStartTime = couponBatchEffectStartTime;
	}
	public Date getCouponBatchEffectEndTime() {
		return couponBatchEffectEndTime;
	}
	public void setCouponBatchEffectEndTime(Date couponBatchEffectEndTime) {
		this.couponBatchEffectEndTime = couponBatchEffectEndTime;
	}
	
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
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponBatchCode() {
		return couponBatchCode;
	}
	public void setCouponBatchCode(String couponBatchCode) {
		this.couponBatchCode = couponBatchCode;
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
	public Integer getGrantType() {
		return grantType;
	}
	public void setGrantType(Integer grantType) {
		this.grantType = grantType;
	}
	public Integer getGrantCount() {
		return grantCount;
	}
	public void setGrantCount(Integer grantCount) {
		this.grantCount = grantCount;
	}
	public Integer getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(Integer isRepeat) {
		this.isRepeat = isRepeat;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Integer getJumpType() {
		return jumpType;
	}
	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}
	public Integer getEffectDays() {
		return effectDays;
	}
	public void setEffectDays(Integer effectDays) {
		this.effectDays = effectDays;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
}
	