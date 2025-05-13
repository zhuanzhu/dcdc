package com.egeo.components.order.vo;

import java.math.BigDecimal;

/**
 * 订单退款记录vo
 *
 * @author graci
 *
 */
public class RefundOrderVO {

	/**
	 * 流水id
	 */
	private Long id;
	/**
	 * 退款时间
	 */
	private String refundTime;
	/**
	 * 操作人名
	 */
	private String operator;
	/**
	 * 已退现金
	 */
	private BigDecimal refundCash;
	/**
	 * 已退积分
	 */
	private BigDecimal refundFubi;

	/**
	 * 已退积点
	 */
	private BigDecimal refundJidian;
	/**
	 * 退款原因
	 */
	private String reason;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 退款数量
	 */
	private Integer refundCount;

	/**
	 * 已退卡劵金额
	 */
	private BigDecimal refundCard;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public BigDecimal getRefundCash() {
		return refundCash;
	}

	public void setRefundCash(BigDecimal refundCash) {
		this.refundCash = refundCash;
	}

	public BigDecimal getRefundFubi() {
		return refundFubi;
	}

	public void setRefundFubi(BigDecimal refundFubi) {
		this.refundFubi = refundFubi;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BigDecimal getRefundJidian() {
		return refundJidian;
	}

	public void setRefundJidian(BigDecimal refundJidian) {
		this.refundJidian = refundJidian;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Integer refundCount) {
		this.refundCount = refundCount;
	}

	public BigDecimal getRefundCard() {
		return refundCard;
	}

	public void setRefundCard(BigDecimal refundCard) {
		this.refundCard = refundCard;
	}
}
