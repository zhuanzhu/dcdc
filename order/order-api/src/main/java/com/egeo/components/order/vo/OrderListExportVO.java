package com.egeo.components.order.vo;

import java.math.BigDecimal;

/**
 * 订单清单导出excelVO
 *
 * @author graci
 *
 */
public class OrderListExportVO {
	/**
	 * "订单号",
	 */
	private String orderCode;
	/**
	 * "订单状态",
	 */
	private String orderStatus;
	/**
	 * "支付状态",
	 */
	private String payStatus;
	/**
	 * "是否积分支付",
	 */
	private String useFubi;
	/**
	 * "现金支付方式",
	 */
	private String cashPayMethod;
	/**
	 * "订单结算总额",
	 */
	private BigDecimal sum;
	/**
	 * "积分支付金额",
	 */
	private BigDecimal payByFubi;
	/**
	 * "现金实付金额",
	 */
	private BigDecimal payByCash;

	/**
	 * "现金实付金额",
	 */
	private BigDecimal payByBuyCard;

	/**
	 * 积点实付金额
	 */
	private BigDecimal payByJiDian;

	private BigDecimal refundCash;

	private BigDecimal refundFubi;

	private BigDecimal refundJidian;

	private BigDecimal refundCard;

	private BigDecimal refundSum;

	/**
	 * "下单时间",
	 */
	private String createTime;

	private String payTime;




	/**
	 * '结算方式:1预付费 2后付费'
	 **/
	private String settleMethod;

	private String buyCardType;

	private String buyCardStr;

	private String buyCardRefundStr;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getUseFubi() {
		return useFubi;
	}

	public void setUseFubi(String useFubi) {
		this.useFubi = useFubi;
	}

	public String getCashPayMethod() {
		return cashPayMethod;
	}

	public void setCashPayMethod(String cashPayMethod) {
		this.cashPayMethod = cashPayMethod;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public BigDecimal getPayByFubi() {
		return payByFubi;
	}

	public void setPayByFubi(BigDecimal payByFubi) {
		this.payByFubi = payByFubi;
	}

	public BigDecimal getPayByCash() {
		return payByCash;
	}

	public void setPayByCash(BigDecimal payByCash) {
		this.payByCash = payByCash;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPayByJiDian() {
		return payByJiDian;
	}

	public void setPayByJiDian(BigDecimal payByJiDian) {
		this.payByJiDian = payByJiDian;
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

	public BigDecimal getRefundJidian() {
		return refundJidian;
	}

	public void setRefundJidian(BigDecimal refundJidian) {
		this.refundJidian = refundJidian;
	}

	public BigDecimal getRefundSum() {
		return refundSum;
	}

	public void setRefundSum(BigDecimal refundSum) {
		this.refundSum = refundSum;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getPayByBuyCard() {
		return payByBuyCard;
	}

	public void setPayByBuyCard(BigDecimal payByBuyCard) {
		this.payByBuyCard = payByBuyCard;
	}

	public BigDecimal getRefundCard() {
		return refundCard;
	}

	public void setRefundCard(BigDecimal refundCard) {
		this.refundCard = refundCard;
	}

	public String getBuyCardStr() {
		return buyCardStr;
	}

	public void setBuyCardStr(String buyCardStr) {
		this.buyCardStr = buyCardStr;
	}

	public String getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(String settleMethod) {
		this.settleMethod = settleMethod;
	}

	public String getBuyCardType() {
		return buyCardType;
	}

	public void setBuyCardType(String buyCardType) {
		this.buyCardType = buyCardType;
	}

	public String getBuyCardRefundStr() {
		return buyCardRefundStr;
	}

	public void setBuyCardRefundStr(String buyCardRefundStr) {
		this.buyCardRefundStr = buyCardRefundStr;
	}
}
