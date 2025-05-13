package com.egeo.components.order.vo;

import java.math.BigDecimal;

public class OrderSortExportVO {

	/**
	 * 订单号
	 */
	private String orderCode;
	/**
	 * 子订单编号
	 */
	private String childCode;
	/**
	 * 快递单号
	 */
	private String deliveryCode;
	/**
	 * 箱号
	 */
	private String soBoxCode;
	/**
	 * 物流公司,
	 */
	private String deliveryCompany;

	/**
	 * 发送日期
	 */
	private String deliveryDate;
	/**
	 * 收货人,
	 */
	private String receiverName;
	/**
	 * 收货地址,
	 */
	private String receiverAddress;
	/**
	 * 联系电话,
	 */
	private String receiverMobile;
	private Long merchantId;
	/**
	 * 类目
	 */
	private String productCategory;
	/**
	 * 商品编码
	 */
	private String merchantProductSerialNumber;

	/**
	 * 商品名称,
	 */
	private String puName;
	/**
	 * 规格,
	 */
	private String puSpec;
	/**
	 * 数量,
	 */
	private Integer puCount;

	/**
	 * 已退数量
	 */
	private Integer refundCount;

	/**
	 * 已退金额
	 */
	private BigDecimal refundAmount;
	/**
	 * 单价,
	 */
	private BigDecimal price;
	/**
	 * 总价,
	 */
	private BigDecimal sum;
	/**
	 * 会员姓名,
	 */
	private String userName;
	/**
	 * 会员编号,
	 */
	private String memberCode;
	/**
	 * 会员公司,
	 */
	private String companyName;
	/**
	 * 用户邮箱,
	 */
	private String userEmail;
	/**
	 * 是否开发票,
	 */
	private String invoiceExist;
	private String merchantName;

	/**
	 * 发票编号
	 */
	private String invoiceCode;
	/**
	 * 发票抬头,
	 */
	private String invoiceTitle;
	/**
	 * 发票内容,
	 */
	private String invoiceContent;
	/**
	 * 发票类型,
	 */
	private String invoiceForm;
	/**
	 * 抬头类型
	 */
	private String invoiceTitleType;
	/**
	 * 抬头类型
	 */
	private BigDecimal supplierPrice;
	/**
	 * 抬头类型
	 */
	private Long supplierId;
	/**
	 * 抬头类型
	 */
	private String supplierName;
	private String supplierAccount;

	private BigDecimal deliveryFeeAver;

	private String orderCreateTime;

	/**
	 * 税率
	 */
	private String taxRate;

	private Integer source;

	private String snapshot;

	private Long puId;

	private Long userId;

	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAccount() {
		return supplierAccount;
	}

	public void setSupplierAccount(String supplierAccount) {
		this.supplierAccount = supplierAccount;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

	public String getSoBoxCode() {
		return soBoxCode;
	}

	public void setSoBoxCode(String soBoxCode) {
		this.soBoxCode = soBoxCode;
	}

	public String getDeliveryCode() {
		return deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	public String getDeliveryCompany() {
		return deliveryCompany;
	}

	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getPuName() {
		return puName;
	}

	public void setPuName(String puName) {
		this.puName = puName;
	}

	public String getPuSpec() {
		return puSpec;
	}

	public void setPuSpec(String puSpec) {
		this.puSpec = puSpec;
	}

	public Integer getPuCount() {
		return puCount;
	}

	public void setPuCount(Integer puCount) {
		this.puCount = puCount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getInvoiceExist() {
		return invoiceExist;
	}

	public void setInvoiceExist(String invoiceExist) {
		this.invoiceExist = invoiceExist;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public String getInvoiceForm() {
		return invoiceForm;
	}

	public void setInvoiceForm(String invoiceForm) {
		this.invoiceForm = invoiceForm;
	}

	public String getInvoiceTitleType() {
		return invoiceTitleType;
	}

	public void setInvoiceTitleType(String invoiceTitleType) {
		this.invoiceTitleType = invoiceTitleType;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public BigDecimal getDeliveryFeeAver() {
		return deliveryFeeAver;
	}

	public void setDeliveryFeeAver(BigDecimal deliveryFeeAver) {
		this.deliveryFeeAver = deliveryFeeAver;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}

	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	public Long getPuId() {
		return puId;
	}

	public void setPuId(Long puId) {
		this.puId = puId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Integer refundCount) {
		this.refundCount = refundCount;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
