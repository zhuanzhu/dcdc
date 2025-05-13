package com.egeo.components.order.vo;

import java.io.Serializable;

/**
 * 
 * @author ghw
 * @date 2018-02-03 17:38:35
 * 客户端创建订单时发票详细页面调用
 */
public class SoInvoiceVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 发票形式 0:纸质发票 1:电子发票
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
	 * 发票明细类型 0:商品明细 1:商品类别
	 */
	private Integer invoiceContentType;
	/**
	 * 纳税人识别号
	 */
	private String tpic;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTpic() {
		return tpic;
	}

	public void setTpic(String tpic) {
		this.tpic = tpic;
	}


}
