package com.egeo.components.order.vo;

import java.math.BigDecimal;
import java.util.List;

public class SoChildWebVO {
	private Long id;
	private String childCode;
	private Long createTime;	// 订单的创建时间
	private List<OrderConfirmGoodsVO>  goodsList;
	private BigDecimal childOrderAmount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChildCode() {
		return childCode;
	}
	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public List<OrderConfirmGoodsVO> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<OrderConfirmGoodsVO> goodsList) {
		this.goodsList = goodsList;
	}
	public BigDecimal getChildOrderAmount() {
		return childOrderAmount;
	}
	public void setChildOrderAmount(BigDecimal childOrderAmount) {
		this.childOrderAmount = childOrderAmount;
	}
}
