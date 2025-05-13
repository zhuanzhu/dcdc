package com.egeo.components.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单详情vo,用于app端用户查询订单列表
 * @author GRACIA
 *
 */
public class SoDetailVO implements Serializable{

	private static final long serialVersionUID = 2441190065683855055L;

	private Long id;
	private String orderCode;
	private BigDecimal payAmount;

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	private Integer orderStatus;
	private Integer goodsAccount;
	private BigDecimal orderAmount;
	private Integer useFubi;
	private List<OrderConfirmGoodsVO> goodsList;
	private boolean unitExist;
	/**
	 * 操作状态 0:取消和去支付 1:仅取消 2:查看物流和确认收货 3:查看物流和删除订单
	 */
	private Integer operateStatus;

	private Integer couponType;	// -1:未使用优惠卷  0:满减卷 1:兑换卷

	private Long createTime;	// 订单的创建时间
	private List<SoChildWebVO> soChildWebList;

	private Integer exchangeCouponType;

	/**
	 * 跳转链接
	 */
	private String jumpUrl;

	/**
	 * 是否子订单：0母订单 1子订单
	 */
	private String ifChildOrder;

	public Integer getExchangeCouponType() {
		return exchangeCouponType;
	}

	public void setExchangeCouponType(Integer exchangeCouponType) {
		this.exchangeCouponType = exchangeCouponType;
	}

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
	public Integer getUseFubi() {
		return useFubi;
	}
	public void setUseFubi(Integer useFubi) {
		this.useFubi = useFubi;
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
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getGoodsAccount() {
		return goodsAccount;
	}
	public void setGoodsAccount(Integer goodsAccount) {
		this.goodsAccount = goodsAccount;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public List<OrderConfirmGoodsVO> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<OrderConfirmGoodsVO> goodsList) {
		this.goodsList = goodsList;
	}
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public List<SoChildWebVO> getSoChildWebList() {
		return soChildWebList;
	}
	public void setSoChildWebList(List<SoChildWebVO> soChildWebList) {
		this.soChildWebList = soChildWebList;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getIfChildOrder() {
		return ifChildOrder;
	}

	public void setIfChildOrder(String ifChildOrder) {
		this.ifChildOrder = ifChildOrder;
	}
}
