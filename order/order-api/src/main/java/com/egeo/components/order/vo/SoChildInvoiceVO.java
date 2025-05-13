package com.egeo.components.order.vo;

import java.math.BigDecimal;

/**
 * 子订单发票列表VO
 * 用于订单发票信息的子订单发票信息列表
 * 
 * @author graci
 *
 */
public class SoChildInvoiceVO {

	private Long id;//子订单id
	private String childCode;
	private Long invoiceId;//发票id
	private Integer invoiceType;
	private String invoiceCode;
	private Integer financeStatus;
	private String remark;
	private String invoiceAttcUrl;
	private BigDecimal invoiceValue;
	private String invoiceTitleContent;
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
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Integer getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public Integer getFinanceStatus() {
		return financeStatus;
	}
	public void setFinanceStatus(Integer financeStatus) {
		this.financeStatus = financeStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInvoiceAttcUrl() {
		return invoiceAttcUrl;
	}
	public void setInvoiceAttcUrl(String invoiceAttcUrl) {
		this.invoiceAttcUrl = invoiceAttcUrl;
	}
	public BigDecimal getInvoiceValue() {
		if (invoiceValue == null){
			return BigDecimal.ZERO;
		}
		return invoiceValue;
	}
	public void setInvoiceValue(BigDecimal invoiceValue) {
		this.invoiceValue = invoiceValue;
	}
	public String getInvoiceTitleContent() {
		return invoiceTitleContent;
	}
	public void setInvoiceTitleContent(String invoiceTitleContent) {
		this.invoiceTitleContent = invoiceTitleContent;
	}
	
	
	
}
