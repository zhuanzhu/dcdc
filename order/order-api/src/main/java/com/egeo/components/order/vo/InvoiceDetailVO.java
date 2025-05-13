package com.egeo.components.order.vo;

/**
 * 客户端查询发票详情VO
 * @author graci
 *
 */
public class InvoiceDetailVO {

	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 发票税务类型 1:增值税发票 2:其他
	 */
	private Integer invoiceTaxType;
	/**
	 * 发票形式 0:纸质发票  1:电子发票
	 */
	private Integer invoiceForm;
	/**
	 * 发票抬头类型：0：个人；1：公司
	 */
	private Integer invoiceTitleType;
	/**
	 * 抬头内容
	 */
	private String invoiceTitleContent;
	/**
	 * 发票明细类型 0:商品明细  1:商品类别
	 */
	private Integer invoiceContentType;
	/**
	 * 纳税人识别号
	 */
	private String taxpayerIdentificationCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getInvoiceTaxType() {
		return invoiceTaxType;
	}
	public void setInvoiceTaxType(Integer invoiceTaxType) {
		this.invoiceTaxType = invoiceTaxType;
	}
	public Integer getInvoiceForm() {
		return invoiceForm;
	}
	public void setInvoiceForm(Integer invoiceForm) {
		this.invoiceForm = invoiceForm;
	}
	public Integer getInvoiceTitleType() {
		return invoiceTitleType;
	}
	public void setInvoiceTitleType(Integer invoiceTitleType) {
		this.invoiceTitleType = invoiceTitleType;
	}
	public String getInvoiceTitleContent() {
		return invoiceTitleContent;
	}
	public void setInvoiceTitleContent(String invoiceTitleContent) {
		this.invoiceTitleContent = invoiceTitleContent;
	}
	public Integer getInvoiceContentType() {
		return invoiceContentType;
	}
	public void setInvoiceContentType(Integer invoiceContentType) {
		this.invoiceContentType = invoiceContentType;
	}
	public String getTaxpayerIdentificationCode() {
		return taxpayerIdentificationCode;
	}
	public void setTaxpayerIdentificationCode(String taxpayerIdentificationCode) {
		this.taxpayerIdentificationCode = taxpayerIdentificationCode;
	}

}
