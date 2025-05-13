package com.egeo.components.order.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 后台订单列表vo
 *
 * @author GRACIA
 *
 */
public class SoListBackVo {
	private String storeName;
	private Long isDetail;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getIsDetail() {
		return isDetail;
	}

	public void setIsDetail(Long isDetail) {
		this.isDetail = isDetail;
	}

	private BigDecimal soAmount;
	private BigDecimal couponDiscount;
	private BigDecimal storeDiscount;
	private BigDecimal discount;
	private BigDecimal payAmount;
	private Long companyId;
	private String companyName;
	private Integer auditStatus;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getSoAmount() {
		return soAmount;
	}

	public void setSoAmount(BigDecimal soAmount) {
		this.soAmount = soAmount;
	}

	public BigDecimal getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(BigDecimal couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public BigDecimal getStoreDiscount() {
		return storeDiscount;
	}

	public void setStoreDiscount(BigDecimal storeDiscount) {
		this.storeDiscount = storeDiscount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
private Long puNum;

	public Long getPuNum() {
		return puNum;
	}

	public void setPuNum(Long puNum) {
		this.puNum = puNum;
	}

	private Date payTime;//付款时间
	private Long id;// 主键
	private Double soDelivery;

	public Double getSoDelivery() {
		return soDelivery;
	}

	public void setSoDelivery(Double soDelivery) {
		this.soDelivery = soDelivery;
	}

	private String orderCode; // 订单编号
	private Date createTime;// 创建时间
	private BigDecimal orderAmount; // 订单金额
	private BigDecimal orderAmountPay;// 订单结算金额
	private BigDecimal orderAmountBenefit;// 优惠金额
	private Integer orderStatus;// 订单状态编码
	private String orderStatusStr;// 订单状态的字符串说明
	// private Integer paymentType;//支付方式 1.积分 2.支付宝 3. 微信 4. 积分+支付宝 5.微信+支付宝
	private Long userId; // 订单用户的id
	private String mobile; // 用户的联系方式
	private String mail;
	private String userName;
	private boolean useFubi;// 是否使用积分
	private Integer cashPayType;// 现金付款方式 1 支付宝 2 微信 3 银联 4 建行
	private Integer saleWay;// 订单类型：1正常销售、2团购、3兑换券
	private Integer orderChannel;// 订单渠道 : 0 andoird 1 IOS 2 微信端 3 web端
	private BigDecimal orderCardPaid;
	public Integer getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(Integer orderChannel) {
		this.orderChannel = orderChannel;
	}

	public Integer getSaleWay() {
		return saleWay;
	}

	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isUseFubi() {
		return useFubi;
	}

	public void setUseFubi(boolean useFubi) {
		this.useFubi = useFubi;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 现金支付金额
	 */
	private BigDecimal orderPaidByCash;

	/**
	 * 积分支付金额
	 */
	private BigDecimal orderPaidByFubi;

	/**
	 * 订单确认状态 0:未确认，1:已确认，2:已取消
	 */
	private Integer orderConfirmStatus;

	/**
	 * 订单支付状态 0:未支付、1:已支付、2:已退款'
	 */
	private Integer orderPayStatus;

	public Integer getCashPayType() {
		return cashPayType;
	}

	public void setCashPayType(Integer cashPayType) {
		this.cashPayType = cashPayType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusStr() {
		return this.orderStatusStr;
	}

	public void setOrderStatusStr(String orderStatusStr) {
		this.orderStatusStr = orderStatusStr;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getOrderAmountPay() {
		return orderAmountPay;
	}

	public void setOrderAmountPay(BigDecimal orderAmountPay) {
		this.orderAmountPay = orderAmountPay;
	}

	public BigDecimal getOrderAmountBenefit() {
		return orderAmountBenefit;
	}

	public void setOrderAmountBenefit(BigDecimal orderAmountBenefit) {
		this.orderAmountBenefit = orderAmountBenefit;
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

	public BigDecimal getOrderPaidByCash() {
		return orderPaidByCash;
	}

	public void setOrderPaidByCash(BigDecimal orderPaidByCash) {
		this.orderPaidByCash = orderPaidByCash;
	}

	public BigDecimal getOrderPaidByFubi() {
		return orderPaidByFubi;
	}

	public void setOrderPaidByFubi(BigDecimal orderPaidByFubi) {
		this.orderPaidByFubi = orderPaidByFubi;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public BigDecimal getOrderCardPaid() {
		return orderCardPaid;
	}

	public void setOrderCardPaid(BigDecimal orderCardPaid) {
		this.orderCardPaid = orderCardPaid;
	}
}
