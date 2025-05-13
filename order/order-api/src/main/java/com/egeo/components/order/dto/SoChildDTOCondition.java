package com.egeo.components.order.dto;

import java.math.BigDecimal;

public class SoChildDTOCondition extends SoChildDTO {

	private static final long serialVersionUID = 1L;
	
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
	
	private String mail;
	
	private Integer orderPayStatus;
	
	private BigDecimal promotionAver;
	
	private String lastOperatorMail;
	
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getOrderPayStatus() {
		return orderPayStatus;
	}

	public void setOrderPayStatus(Integer orderPayStatus) {
		this.orderPayStatus = orderPayStatus;
	}

	public BigDecimal getPromotionAver() {
		return promotionAver;
	}

	public void setPromotionAver(BigDecimal promotionAver) {
		this.promotionAver = promotionAver;
	}


	public String getLastOperatorMail() {
		return lastOperatorMail;
	}

	public void setLastOperatorMail(String lastOperatorMail) {
		this.lastOperatorMail = lastOperatorMail;
	}	
	
	
}
