package com.egeo.components.order.vo;

import java.io.Serializable;

/**
 * 
 * @author wyy
 * @date 2018-08-10 10:40:09
 */
public class InvoiceSimpleVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 抬头内容/名称
	 */
	private String titleContent;
	/**
	 * 纳税人识别号
	 */
	private String taxpayerIdentificationCode;
	
	/**
	 * 是否设为默认  0:否 1:是
	 */
	private Integer isDefault;
	
	private Integer isFullInfo;	// 信息是否完整 0:否 1:是
	
	/**
	 * 发票形式 0:纸质发票  1:电子发票 2:增值税发票
	 */
	private Integer invoiceForm;
	/**
	 * 发票抬头类型：0：个人；1：公司
	 */
	private Integer invoiceTitleType;
	/**
	 * 发票明细类型 0:商品明细  1:商品类别
	 */
	private Integer invoiceContentType;
	
	/**
	 * 订单发票的id
	 */
	private Long invoiceId;
	
	/**
	 * 公共发票的数量
	 */
	private Integer commonInvoiceListSize;
	
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

	public Integer getInvoiceContentType() {
		return invoiceContentType;
	}

	public void setInvoiceContentType(Integer invoiceContentType) {
		this.invoiceContentType = invoiceContentType;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 抬头内容/名称
	 * @return 抬头内容/名称
	 */
	public String getTitleContent() {
		return titleContent;
	}

	/**
	 * 抬头内容/名称
	 * @param titleContent 抬头内容/名称
	 */
	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
	}	
	/**
	 * 纳税人识别号
	 * @return 纳税人识别号
	 */
	public String getTaxpayerIdentificationCode() {
		return taxpayerIdentificationCode;
	}

	/**
	 * 纳税人识别号
	 * @param taxpayerIdentificationCode 纳税人识别号
	 */
	public void setTaxpayerIdentificationCode(String taxpayerIdentificationCode) {
		this.taxpayerIdentificationCode = taxpayerIdentificationCode;
	}	
	
	/**
	 * 是否设为默认  0:否 1:是
	 * @return 是否设为默认  0:否 1:是
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * 是否设为默认  0:否 1:是
	 * @param isDefault 是否设为默认  0:否 1:是
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}	
	
	public Integer getIsFullInfo() {
		return isFullInfo;
	}

	public void setIsFullInfo(Integer isFullInfo) {
		this.isFullInfo = isFullInfo;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getCommonInvoiceListSize() {
		return commonInvoiceListSize;
	}

	public void setCommonInvoiceListSize(Integer commonInvoiceListSize) {
		this.commonInvoiceListSize = commonInvoiceListSize;
	}	
}
	