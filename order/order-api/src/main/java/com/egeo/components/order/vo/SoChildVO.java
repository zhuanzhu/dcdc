package com.egeo.components.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ghw
 * @date 2018-01-31 19:02:12
 */
public class SoChildVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String soThirdpartySoChildStatusStr;
	private Date thirdpartyPayTime;
	private BigDecimal thirdpartySoChildPayAmount;
	private String userName;
	private String userMail;
	private String soCode;
	private String merchantName;
	private String soChildCode;

	private Integer soStatus;
	private Integer soConfirmStatus;
	private Integer soPayStatus;
	private Integer source ;
	private BigDecimal thirdpatyDiscountAmount;
	private String ext;
	/**
	 * 删除状态：0未删除 2已删除
	 */
	private Integer childDeleteStatus;
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getSoThirdpartySoChildStatusStr() {
		return soThirdpartySoChildStatusStr;
	}

	public void setSoThirdpartySoChildStatusStr(String soThirdpartySoChildStatusStr) {
		this.soThirdpartySoChildStatusStr = soThirdpartySoChildStatusStr;
	}

	public Date getThirdpartyPayTime() {
		return thirdpartyPayTime;
	}

	public void setThirdpartyPayTime(Date thirdpartyPayTime) {
		this.thirdpartyPayTime = thirdpartyPayTime;
	}

	public BigDecimal getThirdpartySoChildPayAmount() {
		return thirdpartySoChildPayAmount;
	}

	public void setThirdpartySoChildPayAmount(BigDecimal thirdpartySoChildPayAmount) {
		this.thirdpartySoChildPayAmount = thirdpartySoChildPayAmount;
	}

	public Integer getSoStatus() {
		return soStatus;
	}

	public void setSoStatus(Integer soStatus) {
		this.soStatus = soStatus;
	}

	public Integer getSoConfirmStatus() {
		return soConfirmStatus;
	}

	public void setSoConfirmStatus(Integer soConfirmStatus) {
		this.soConfirmStatus = soConfirmStatus;
	}

	public Integer getSoPayStatus() {
		return soPayStatus;
	}

	public void setSoPayStatus(Integer soPayStatus) {
		this.soPayStatus = soPayStatus;
	}

	public String getSoChildCode() {
		return soChildCode;
	}

	public void setSoChildCode(String soChildCode) {
		this.soChildCode = soChildCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getSoCode() {
		return soCode;
	}

	public void setSoCode(String soCode) {
		this.soCode = soCode;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	private String thirdpartySoChildId;

	private Integer thirdpartySoChildStatus;

	public Integer getThirdpartySoChildStatus() {
		return thirdpartySoChildStatus;
	}

	public void setThirdpartySoChildStatus(Integer thirdpartySoChildStatus) {
		this.thirdpartySoChildStatus = thirdpartySoChildStatus;
	}

	public String getThirdpartySoChildId() {
		return thirdpartySoChildId;
	}

	public void setThirdpartySoChildId(String thirdpartySoChildId) {
		this.thirdpartySoChildId = thirdpartySoChildId;
	}

	//主键
	private Long id;
	private Long preSell;
	private BigDecimal soChildAmount;
	private BigDecimal discount;
	private BigDecimal payAmount;

	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}

	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}

	/**
	 * 物流公司名
	 */
	private String deliveryCompanyName;

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getStoreDiscount() {
		return storeDiscount;
	}

	public void setStoreDiscount(BigDecimal storeDiscount) {
		this.storeDiscount = storeDiscount;
	}

	public BigDecimal getSoChildAmount() {
		return soChildAmount;
	}

	public void setSoChildAmount(BigDecimal soChildAmount) {
		this.soChildAmount = soChildAmount;
	}

	public Long getPreSell() {
		return preSell;
	}

	public void setPreSell(Long preSell) {
		this.preSell = preSell;
	}

	//子订单编号
	private String childCode;

	//母订单编号
	private String orderCode;
	/**
	 * 原来的子订单id
	 */
	private Long ordinaryId;

	/**
	 * 原始子订单编号
	 */
	private String ordinaryCode;

	private Long supplierId;
	/**
	 * 母订单id
	 */
	private Long soId;
	/**
	 * 子订单分配金额
	 */
	private BigDecimal amount;
	/**
	 * 物流id
	 */
	private Long deliveryId;
	/**
	 * 订单物流信息 0:未分拣，1:分拣中，2:已分拣
	 */
	private Integer deliveryStatus;

	/**
	 * 子订单取消状态 0-未取消;1-已取消;
	 */
	private Integer cancelStatus;

	/**
	 * 履约方ID（运营方ID）
	 */
	private Long performingParty;
	/**
	 * 发票id
	 */
	private Long invoiceId;
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 最后修改人id
	 */
	private Long lastOperatorId;

	private String lastOperatorName;

	/**
	 * 平台id
	 */
	private Long platformId;

	/**
	 * 所属用户
	 */
	private String mail;
	/**
	 * 预售商品应发货截止日期
	 */
	private Date deliverEndTime;

	/**
	 * 订单支付状态 0:未支付、1:已支付、2:已退款
	 */
	private Integer orderPayStatus;

	/**
	 * 商品平摊优惠金额
	 */
	private BigDecimal promotionAver;

	/**
	 * 第三方订单类型   0:无第三方订单  1:话费充值
	 */
	private Integer thirdpartyType;
	/**
	 * 运费金额
	 */
	private BigDecimal deliveryFee;
	/**
	 * 是否记录运费(0-否 1-是)
	 */
	private Integer needCountDeliveryFee;
	/**
	 * 商品总金额
	 */
	private BigDecimal productAmount;
	/**
	 * 运费优惠金额
	 */
	private BigDecimal deliveryFeeDiscount;
	/**
	 * 优惠券优惠金额
	 */
	private BigDecimal couponDiscount;
	/**
	 * 门店优惠金额
	 */
	private BigDecimal storeDiscount;
	/**
	 * 优惠券ID
	 */
	private Long couponId;
	/**
	 * 运费券ID
	 */
	private Long deliveryCouponId;
	/**
	 * 原始运费金额,不做拆分
	 */
	private BigDecimal ordinaryDeliveryFee;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public BigDecimal getOrdinaryDeliveryFee() {
		return ordinaryDeliveryFee;
	}

	public void setOrdinaryDeliveryFee(BigDecimal ordinaryDeliveryFee) {
		this.ordinaryDeliveryFee = ordinaryDeliveryFee;
	}

	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}

	public BigDecimal getDeliveryFeeDiscount() {
		return deliveryFeeDiscount;
	}

	public void setDeliveryFeeDiscount(BigDecimal deliveryFeeDiscount) {
		this.deliveryFeeDiscount = deliveryFeeDiscount;
	}

	public BigDecimal getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(BigDecimal couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Long getDeliveryCouponId() {
		return deliveryCouponId;
	}

	public void setDeliveryCouponId(Long deliveryCouponId) {
		this.deliveryCouponId = deliveryCouponId;
	}

	public Integer getNeedCountDeliveryFee() {
		return needCountDeliveryFee;
	}

	public void setNeedCountDeliveryFee(Integer needCountDeliveryFee) {
		this.needCountDeliveryFee = needCountDeliveryFee;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getLastOperatorName() {
		return lastOperatorName;
	}

	public void setLastOperatorName(String lastOperatorName) {
		this.lastOperatorName = lastOperatorName;
	}

	public String getOrdinaryCode() {
		return ordinaryCode;
	}

	public void setOrdinaryCode(String ordinaryCode) {
		this.ordinaryCode = ordinaryCode;
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

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 原来的子订单id
	 * @return 原来的子订单id
	 */
	public Long getOrdinaryId() {
		return ordinaryId;
	}

	/**
	 * 原来的子订单id
	 * @param ordinaryId 原来的子订单id
	 */
	public void setOrdinaryId(Long ordinaryId) {
		this.ordinaryId = ordinaryId;
	}

	/**
	 * 母订单id
	 * @return 母订单id
	 */
	public Long getSoId() {
		return soId;
	}

	/**
	 * 母订单id
	 * @param soId 母订单id
	 */
	public void setSoId(Long soId) {
		this.soId = soId;
	}
	/**
	 * 物流id
	 * @return 物流id
	 */
	public Long getDeliveryId() {
		return deliveryId;
	}

	/**
	 * 物流id
	 * @param deliveryId 物流id
	 */
	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	/**
	 * 订单物流信息 0:未分拣，1:分拣中，2:已分拣
	 * @return 订单物流信息 0:未分拣，1:分拣中，2:已分拣
	 */
	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	/**
	 * 订单物流信息 0:未分拣，1:分拣中，2:已分拣
	 * @param deliveryStatus 订单物流信息 0:未分拣，1:分拣中，2:已分拣
	 */
	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	/**
	 * 发票id
	 * @return 发票id
	 */
	public Long getInvoiceId() {
		return invoiceId;
	}

	/**
	 * 发票id
	 * @param invoiceId 发票id
	 */
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 最后修改人id
	 * @return 最后修改人id
	 */
	public Long getLastOperatorId() {
		return lastOperatorId;
	}

	/**
	 * 最后修改人id
	 * @param lastOperatorId 最后修改人id
	 */
	public void setLastOperatorId(Long lastOperatorId) {
		this.lastOperatorId = lastOperatorId;
	}
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Long getPerformingParty() {
		return performingParty;
	}

	public void setPerformingParty(Long performingParty) {
		this.performingParty = performingParty;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getThirdpartyType() {
		return thirdpartyType;
	}

	public void setThirdpartyType(Integer thirdpartyType) {
		this.thirdpartyType = thirdpartyType;
	}

	public Date getDeliverEndTime() {
		return deliverEndTime;
	}

	public void setDeliverEndTime(Date deliverEndTime) {
		this.deliverEndTime = deliverEndTime;
	}

	public BigDecimal getThirdpatyDiscountAmount() {
		return thirdpatyDiscountAmount;
	}

	public void setThirdpatyDiscountAmount(BigDecimal thirdpatyDiscountAmount) {
		this.thirdpatyDiscountAmount = thirdpatyDiscountAmount;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Integer getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(Integer cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	public Integer getChildDeleteStatus() {
		return childDeleteStatus;
	}

	public void setChildDeleteStatus(Integer childDeleteStatus) {
		this.childDeleteStatus = childDeleteStatus;
	}
}
