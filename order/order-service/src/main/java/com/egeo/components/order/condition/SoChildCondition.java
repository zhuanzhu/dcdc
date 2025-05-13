package com.egeo.components.order.condition;

import java.math.BigDecimal;
import java.util.Date;

import com.egeo.components.order.po.SoChildPO;

/**
 * 
 * @author jiang
 * @date 2018-01-29 09:55:24
 */
public class SoChildCondition extends SoChildPO {
	private static final long serialVersionUID = 1L;

	private BigDecimal discount;
	private Long userId;
	private Integer orderConfirmStatus;
	private Integer orderPayStatus;

	private BigDecimal promotionAver;

	/**
	 * 发票金额
	 */
	private BigDecimal invoiceValue;
	
	/**
	 * 发票状态：0:未开 1:已开
	 */
	private Integer invoiceStatus;	
	
	/**
	 * 
	 */
	private String invoiceCode;
	
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	
	private Date createTimeStart;

	private Date createTimeEnd;
	
	private Date updateTimeStart;
	
	private Date updateTimeEnd;
	
	private String orderCode;
	
	private Date orderPaymentConfirmDate;
	
	private String goodReceiverName;
	
	private String goodReceiverMobile;
	
	private String goodReceiverPhone;
	
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getOrderPaymentConfirmDate() {
		return orderPaymentConfirmDate;
	}

	public void setOrderPaymentConfirmDate(Date orderPaymentConfirmDate) {
		this.orderPaymentConfirmDate = orderPaymentConfirmDate;
	}

	public String getGoodReceiverName() {
		return goodReceiverName;
	}

	public void setGoodReceiverName(String goodReceiverName) {
		this.goodReceiverName = goodReceiverName;
	}

	public String getGoodReceiverMobile() {
		return goodReceiverMobile;
	}

	public void setGoodReceiverMobile(String goodReceiverMobile) {
		this.goodReceiverMobile = goodReceiverMobile;
	}

	public String getGoodReceiverPhone() {
		return goodReceiverPhone;
	}

	public void setGoodReceiverPhone(String goodReceiverPhone) {
		this.goodReceiverPhone = goodReceiverPhone;
	}

	public BigDecimal getPromotionAver() {
		return promotionAver;
	}

	public void setPromotionAver(BigDecimal promotionAver) {
		this.promotionAver = promotionAver;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public BigDecimal getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(BigDecimal invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(Date updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

}
	