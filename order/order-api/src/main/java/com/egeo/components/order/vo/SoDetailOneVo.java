package com.egeo.components.order.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 客户端查询单个订单详情Vo
 *
 * @author GRACIA
 */
public class SoDetailOneVo {

	public Integer getExchangeCouponType() {
		return exchangeCouponType;
	}

	public void setExchangeCouponType(Integer exchangeCouponType) {
		this.exchangeCouponType = exchangeCouponType;
	}

	private Integer exchangeCouponType;
	private String payTime;
	private String deliveryDate;
	private Integer  orderConfirmStatus;
	private Integer orderPayStatus;

	public Integer getOrderConfirmStatus() {
		return orderConfirmStatus;
	}

	public void setOrderConfirmStatus(Integer orderConfirmStatus) {
		this.orderConfirmStatus = orderConfirmStatus;
	}

	public Integer getOrderPayStatus() {
		return orderPayStatus;
	}

	public void setOrderPayStatus(Integer orderPayStatus) {
		this.orderPayStatus = orderPayStatus;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	private Long id;
	private BigDecimal puAmount;
	private BigDecimal couponDiscount;
	private BigDecimal storeDiscount;
	private Integer orderType;

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	private String userMail;


	public BigDecimal getPuAmount() {
		return puAmount;
	}

	public void setPUAmount(BigDecimal puAmount) {
		this.puAmount = puAmount;
	}

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

	private String orderCode;
	private Integer status;
	private String statusStr;
	private String createTime;
	private boolean useFubi;
	private Integer payCashMethod;
	private String payCashMethodStr;
	private Double goodsAmount;
	private Double deliveryFee;
	private Double orderAmount;
	private Double paidByFubi;
	private Double paidByCash;
	private boolean unitExist;
	private Integer operateStatus;
	private String rechangePhone;
	private Integer couponType;    // 优惠卷类型: -1:未使用优惠卷  0:满减卷 1:兑换卷
	private Integer orderPromotionDiscount;    // 优惠金额

	private Boolean isShowAddr;    // 是否展示收货地址

	/**
	 * 跳转第三方订单详情url
	 */
	private String jumpUrl;

	private Double paidByJiDian;

	private String payType;

	private String payTypeName;

	/**
	 * 是否子订单：0母订单 1子订单
	 */
	private String ifChildOrder;

	public Integer getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(Integer operateStatus) {
		this.operateStatus = operateStatus;
	}

	public boolean getUnitExist() {
		return unitExist;
	}

	public void setUnitExist(boolean unitExist) {
		this.unitExist = unitExist;
	}

	public String getPayCashMethodStr() {
		return payCashMethodStr;
	}

	public void setPayCashMethodStr(String payCashMethodStr) {
		this.payCashMethodStr = payCashMethodStr;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Double getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Double goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public boolean isUseFubi() {
		return useFubi;
	}

	public void setUseFubi(boolean useFubi) {
		this.useFubi = useFubi;
	}

	public Integer getPayCashMethod() {
		return payCashMethod;
	}

	public void setPayCashMethod(Integer payCashMethod) {
		this.payCashMethod = payCashMethod;
	}

	public Double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Double getPaidByFubi() {
		return paidByFubi;
	}

	public void setPaidByFubi(Double paidByFubi) {
		this.paidByFubi = paidByFubi;
	}

	public Double getPaidByCash() {
		return paidByCash;
	}

	public void setPaidByCash(Double paidByCash) {
		this.paidByCash = paidByCash;
	}

	public String getRechangePhone() {
		return rechangePhone;
	}

	public void setRechangePhone(String rechangePhone) {
		this.rechangePhone = rechangePhone;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public Integer getOrderPromotionDiscount() {
		return orderPromotionDiscount;
	}

	public void setOrderPromotionDiscount(Integer orderPromotionDiscount) {
		this.orderPromotionDiscount = orderPromotionDiscount;
	}

	public Boolean getIsShowAddr() {
		return isShowAddr;
	}

	public void setIsShowAddr(Boolean isShowAddr) {
		this.isShowAddr = isShowAddr;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public Double getPaidByJiDian() {
		return paidByJiDian;
	}

	public void setPaidByJiDian(Double paidByJiDian) {
		this.paidByJiDian = paidByJiDian;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public String getIfChildOrder() {
		return ifChildOrder;
	}

	public void setIfChildOrder(String ifChildOrder) {
		this.ifChildOrder = ifChildOrder;
	}
}
