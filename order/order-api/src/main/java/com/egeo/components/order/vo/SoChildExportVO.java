package com.egeo.components.order.vo;

/**
 * 子订单导出vo
 * @author graci
 *
 */
public class SoChildExportVO {

	/**
	 * 订单号
	 */
	private String orderCode;
	/**
	 * 子订单编号
	 */
	private String childCode;
	/**
	 * 运单号
	 */
	private String deliveryCode;
	/**
	 * 物流公司
	 */
	private String deliveryCompany;
	/**
	 * 收货人姓名
	 */
	private String receiverName;
	/**
	 * 收货地址
	 */
	private String receiverAddress;
	/**
	 * 联系电话
	 */
	private String receiverMobile;
	/**
	 * 商品名称
	 */
	private String puName;
	/**
	 * 数量
	 */
	private Integer num;
	
	/**
	 * 单价
	 */
	private Double price;
	
	/**
	 * 总价
	 */
	private Double sum;
	/**
	 * 会员姓名
	 */
	private String userName;
	
	/**
	 * 会员编号
	 */
	private String userCode;
	/**
	 * 会员公司
	 */
	private String companyName;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 是否开发票
	 */
	private String invoiceExist;
	/**
	 * 发票抬头
	 */
	private String invoiceTitle;
	/**
	 * 发票内容
	 */
	private String invoiceContent;
	
	/**
	 * 发票形式
	 */
	private String invoiceType;
	
	/**
	 * 发票抬头类型
	 */
	private String titleType;

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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}
	
	
}
