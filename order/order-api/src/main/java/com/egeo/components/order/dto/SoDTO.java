package com.egeo.components.order.dto;

import com.egeo.components.order.vo.RefundVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jiang
 * @date 2018-01-29 17:56:48
 */
public class SoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long couponUnitId;

	public Long getCouponUnitId() {
		return couponUnitId;
	}

	public void setCouponUnitId(Long couponUnitId) {
		this.couponUnitId = couponUnitId;
	}

	private Long storeId;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	private BigDecimal limitFuBiPayAmount;

	public BigDecimal getLimitFuBiPayAmount() {
		return limitFuBiPayAmount;
	}

	public void setLimitFuBiPayAmount(BigDecimal limitFuBiPayAmount) {
		this.limitFuBiPayAmount = limitFuBiPayAmount;
	}

	/**
	 *
	 */
	private String orderCode;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 订单总金额(优惠减免前商品,运费等等总额)
	 */
	private BigDecimal orderAmount;

	/**
	 * 订单结算金额(实付总额)
	 */
	private BigDecimal orderAmountPay;

	/**
	 * 订单商品总金额
	 */
	private BigDecimal productAmount;

	/**
	 * 订单状态(10已取消;0-待付款;1-已付款;2-已发货;3-已收货;4-已完成;5-预退款;6已退款;7-部分发货;8-换货中)
	 */
	private Integer orderStatus;

	/**
	 * 订单确认状态  0:未确认，1:已确认，2:已取消
	 */
	private Integer orderConfirmStatus;

	/**
	 * 订单支付状态 0:未支付、1:已支付、2:已退款
	 */
	private Integer orderPayStatus;

	/**
	 * 订单自动完成时间
	 */
	private Date orderAutoCompleteDate;

	/**
	 * 支付确认时间
	 */
	private Date orderPaymentConfirmDate;

	/**
	 * 支付确认类型：0:用户支付确认 1:配送回款确认
	 */
	private Integer orderPaymentConfirmType;

	/**
	 * 运费(实收)
	 */
	private BigDecimal orderDeliveryFee;

	/**
	 * 运费险类型：0  无 1  商家赠送 2  买家购买
	 */
	private Integer orderDeliveryFeeInsuranceType;

	/**
	 * 运费险金额
	 */
	private BigDecimal orderDeliveryFeeInsuranceAmount;

	/**
	 * 现金付款方式 1 支付宝 2 微信 3 银联 4 建行
	 */
	private Integer cashPayType;

	/**
	 * 现金支付的金额
	 */
	private BigDecimal orderPaidByCash;

	/**
	 * 优惠券优惠金额
	 */
	private BigDecimal couponDiscount;

	/**
	 * 支付-礼品卡支付的金额
	 */
	private BigDecimal orderPaidByCard;

	/**
	 * 支付-返利余额支付的金额
	 */
	private BigDecimal orderPaidByRebate;

	/**
	 * 积分支付的金额
	 */
	private BigDecimal orderPaidByFubi;

	/**
	 * 是否选择了使用积分
	 */
	private Integer useFubi;

	/**
	 * 订单已优惠金额(满立减)
	 */
	private BigDecimal orderPromotionDiscount;

	/**
	 * 订单赠送的积分
	 */
	private BigDecimal orderGivePoints;

	/**
	 * 核算运费(应收)
	 */
	private BigDecimal deliveryFee;

	/**
	 * 取消原因  1 未支付自动取消 2 用户主动取消 3 客服取消
	 */
	private Integer cancelReason;

	/**
	 * 取消时间
	 */
	private Date orderCancelDate;

	/**
	 * 取消操作人类型：0:用户取消 1:系统取消 2:客服取消
	 */
	private Integer orderCanceOperateType;

	/**
	 * 订单渠道 : 0 andoird 1 IOS 2 微信端 3 web端
	 */
	private Integer orderChannel;

	/**
	 * 发票id 为空视为不开发票
	 */
	private Long invoiceId;

	/**
	 * 0：未删除1：回收站-用户可恢复到02：用户完全删除(客服可协助恢复到0或1)
	 */
	private Integer orderDeleteStatus;

	/**
	 * 0:普通订单，24小时不付款自动取消1:敏感商品订单，30分钟不付款自动取消
	 */
	private Integer paidOnlineThreshold;

	/**
	 * 收货地址id
	 */
	private Long receiverAddressId;

	/**
	 * 订单更新地址
	 */
	private String modifiedAddress;

	/**
	 * 地址更新时间
	 */
	private Date modifyAddressTime;

	/**
	 * 增值税
	 */
	private BigDecimal incrementTaxAmount;

	/**
	 * 关税
	 */
	private BigDecimal customsDutiesAmount;

	/**
	 * 订单配送完成日期
	 */
	private Date deliveryEndtime;

	/**
	 * 发货状态，0：未发货 10：部分发货 20：已发货
	 */
	private Integer deliveryStatus;

	/**
	 * 订单最后一次修改人(客服参与修改的话，此处为客服ID)
	 */
	private Long lastOperatorId;

	/**
	 * 退款时间
	 */
	private Date refundTime;

	/**
	 * 订单备注
	 */
	private String remark;

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;

	/**
	 * 平台id
	 */
	private Long platformId;

	private BigDecimal refundCash;

	private BigDecimal refundFubi;

	private Long companyId;

	private Integer signPlatform;
	/**
	 * 订单类型：1正常销售、2团购、3兑换券
	 */
	private Integer saleWay;

	/**
	 * 门店优惠金额
	 */
	private BigDecimal storeDiscount;

	/**
	 * 运费优惠金额
	 */
	private BigDecimal deliveryFeeDiscount;

	private BigDecimal limitCashPayAmount;

	private Integer auditStatus;

	/**
	 * 扩展字段
	 */
	private String ext;

	/**
	 * 退款信息
	 */
	private RefundVo refundVo;

	/**
	 * 支付积点
	 */
	private BigDecimal orderPaidByJidian;

	/**
	 * 退款积点
	 */
	private BigDecimal refundJidian;

	/**
	 * 支付卡金额
	 */
	private BigDecimal orderCardPaid;

	/**
	 * 退款卡金额
	 */
	private BigDecimal refundCard;

	public BigDecimal getLimitCashPayAmount() {
		return limitCashPayAmount;
	}

	public void setLimitCashPayAmount(BigDecimal limitCashPayAmount) {
		this.limitCashPayAmount = limitCashPayAmount;
	}

	public BigDecimal getStoreDiscount() {
		return storeDiscount;
	}

	public void setStoreDiscount(BigDecimal storeDiscount) {
		this.storeDiscount = storeDiscount;
	}

	public BigDecimal getDeliveryFeeDiscount() {
		return deliveryFeeDiscount;
	}

	public void setDeliveryFeeDiscount(BigDecimal deliveryFeeDiscount) {
		this.deliveryFeeDiscount = deliveryFeeDiscount;
	}

	public Integer getSaleWay() {
		return saleWay;
	}

	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}

	public Integer getSignPlatform() {
		return signPlatform;
	}

	public void setSignPlatform(Integer signPlatform) {
		this.signPlatform = signPlatform;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public BigDecimal getRefundCash() {
		return refundCash;
	}

	public void setRefundCash(BigDecimal refundCash) {
		this.refundCash = refundCash;
	}

	public BigDecimal getRefundFubi() {
		return refundFubi;
	}

	public void setRefundFubi(BigDecimal refundFubi) {
		this.refundFubi = refundFubi;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 *
	 * @return
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 *
	 * @param orderCode
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * 用户ID
	 * @return 用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 订单总金额(优惠减免前商品,运费等等总额)
	 * @return 订单总金额(优惠减免前商品,运费等等总额)
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	/**
	 * 订单总金额(优惠减免前商品,运费等等总额)
	 * @param orderAmount 订单总金额(优惠减免前商品,运费等等总额)
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	/**
	 * 订单结算金额(实付总额)
	 * @return 订单结算金额(实付总额)
	 */
	public BigDecimal getOrderAmountPay() {
		return orderAmountPay;
	}

	/**
	 * 订单结算金额(实付总额)
	 * @param orderAmountPay 订单结算金额(实付总额)
	 */
	public void setOrderAmountPay(BigDecimal orderAmountPay) {
		this.orderAmountPay = orderAmountPay;
	}
	/**
	 * 订单商品总金额
	 * @return 订单商品总金额
	 */
	public BigDecimal getProductAmount() {
		return productAmount;
	}

	/**
	 * 订单商品总金额
	 * @param productAmount 订单商品总金额
	 */
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	/**
	 * 订单状态(10已取消;0-待付款;1-已付款;2-已发货;3-已收货;4-已完成;5-预退款;6已退款;7-部分发货;8-换货中)
	 * @return 订单状态(10已取消;0-待付款;1-已付款;2-已发货;3-已收货;4-已完成;5-预退款;6已退款;7-部分发货;8-换货中)
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 订单状态(10已取消;0-待付款;1-已付款;2-已发货;3-已收货;4-已完成;5-预退款;6已退款;7-部分发货;8-换货中)
	 * @param orderStatus 订单状态(10已取消;0-待付款;1-已付款;2-已发货;3-已收货;4-已完成;5-预退款;6已退款;7-部分发货;8-换货中)
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderAutoCompleteDate() {
		return orderAutoCompleteDate;
	}

	public void setOrderAutoCompleteDate(Date orderAutoCompleteDate) {
		this.orderAutoCompleteDate = orderAutoCompleteDate;
	}

	/**
	 * 订单确认状态  0:未确认，1:已确认，2:已取消
	 * @return 订单确认状态  0:未确认，1:已确认，2:已取消
	 */
	public Integer getOrderConfirmStatus() {
		return orderConfirmStatus;
	}

	/**
	 * 订单确认状态  0:未确认，1:已确认，2:已取消
	 * @param orderConfirmStatus 订单确认状态  0:未确认，1:已确认，2:已取消
	 */
	public void setOrderConfirmStatus(Integer orderConfirmStatus) {
		this.orderConfirmStatus = orderConfirmStatus;
	}
	/**
	 * 订单支付状态 0:未支付、1:已支付、2:已退款
	 * @return 订单支付状态 0:未支付、1:已支付、2:已退款
	 */
	public Integer getOrderPayStatus() {
		return orderPayStatus;
	}

	/**
	 * 订单支付状态 0:未支付、1:已支付、2:已退款
	 * @param orderPayStatus 订单支付状态 0:未支付、1:已支付、2:已退款
	 */
	public void setOrderPayStatus(Integer orderPayStatus) {
		this.orderPayStatus = orderPayStatus;
	}
	/**
	 * 支付确认时间
	 * @return 支付确认时间
	 */
	public Date getOrderPaymentConfirmDate() {
		return orderPaymentConfirmDate;
	}

	/**
	 * 支付确认时间
	 * @param orderPaymentConfirmDate 支付确认时间
	 */
	public void setOrderPaymentConfirmDate(Date orderPaymentConfirmDate) {
		this.orderPaymentConfirmDate = orderPaymentConfirmDate;
	}
	/**
	 * 支付确认类型：0:用户支付确认 1:配送回款确认
	 * @return 支付确认类型：0:用户支付确认 1:配送回款确认
	 */
	public Integer getOrderPaymentConfirmType() {
		return orderPaymentConfirmType;
	}

	/**
	 * 支付确认类型：0:用户支付确认 1:配送回款确认
	 * @param orderPaymentConfirmType 支付确认类型：0:用户支付确认 1:配送回款确认
	 */
	public void setOrderPaymentConfirmType(Integer orderPaymentConfirmType) {
		this.orderPaymentConfirmType = orderPaymentConfirmType;
	}
	/**
	 * 运费(实收)
	 * @return 运费(实收)
	 */
	public BigDecimal getOrderDeliveryFee() {
		return orderDeliveryFee;
	}

	/**
	 * 运费(实收)
	 * @param orderDeliveryFee 运费(实收)
	 */
	public void setOrderDeliveryFee(BigDecimal orderDeliveryFee) {
		this.orderDeliveryFee = orderDeliveryFee;
	}
	/**
	 * 运费险类型：0  无 1  商家赠送 2  买家购买
	 * @return 运费险类型：0  无 1  商家赠送 2  买家购买
	 */
	public Integer getOrderDeliveryFeeInsuranceType() {
		return orderDeliveryFeeInsuranceType;
	}

	/**
	 * 运费险类型：0  无 1  商家赠送 2  买家购买
	 * @param orderDeliveryFeeInsuranceType 运费险类型：0  无 1  商家赠送 2  买家购买
	 */
	public void setOrderDeliveryFeeInsuranceType(Integer orderDeliveryFeeInsuranceType) {
		this.orderDeliveryFeeInsuranceType = orderDeliveryFeeInsuranceType;
	}
	/**
	 * 运费险金额
	 * @return 运费险金额
	 */
	public BigDecimal getOrderDeliveryFeeInsuranceAmount() {
		return orderDeliveryFeeInsuranceAmount;
	}

	/**
	 * 运费险金额
	 * @param orderDeliveryFeeInsuranceAmount 运费险金额
	 */
	public void setOrderDeliveryFeeInsuranceAmount(BigDecimal orderDeliveryFeeInsuranceAmount) {
		this.orderDeliveryFeeInsuranceAmount = orderDeliveryFeeInsuranceAmount;
	}
	/**
	 * 现金付款方式 1 支付宝 2 微信 3 银联 4 建行
	 * @return 现金付款方式 1 支付宝 2 微信 3 银联 4 建行
	 */
	public Integer getCashPayType() {
		return cashPayType;
	}

	/**
	 * 现金付款方式 1 支付宝 2 微信 3 银联 4 建行
	 * @param cashPayType 现金付款方式 1 支付宝 2 微信 3 银联 4 建行
	 */
	public void setCashPayType(Integer cashPayType) {
		this.cashPayType = cashPayType;
	}
	/**
	 * 现金支付的金额
	 * @return 现金支付的金额
	 */
	public BigDecimal getOrderPaidByCash() {
		return orderPaidByCash;
	}

	/**
	 * 现金支付的金额
	 * @param orderPaidByCash 现金支付的金额
	 */
	public void setOrderPaidByCash(BigDecimal orderPaidByCash) {
		this.orderPaidByCash = orderPaidByCash;
	}
	/**
	 * 支付-礼品卡支付的金额
	 * @return 支付-礼品卡支付的金额
	 */
	public BigDecimal getOrderPaidByCard() {
		return orderPaidByCard;
	}

	/**
	 * 支付-礼品卡支付的金额
	 * @param orderPaidByCard 支付-礼品卡支付的金额
	 */
	public void setOrderPaidByCard(BigDecimal orderPaidByCard) {
		this.orderPaidByCard = orderPaidByCard;
	}
	/**
	 * 支付-返利余额支付的金额
	 * @return 支付-返利余额支付的金额
	 */
	public BigDecimal getOrderPaidByRebate() {
		return orderPaidByRebate;
	}

	/**
	 * 支付-返利余额支付的金额
	 * @param orderPaidByRebate 支付-返利余额支付的金额
	 */
	public void setOrderPaidByRebate(BigDecimal orderPaidByRebate) {
		this.orderPaidByRebate = orderPaidByRebate;
	}
	/**
	 * 积分支付的金额
	 * @return 积分支付的金额
	 */
	public BigDecimal getOrderPaidByFubi() {
		return orderPaidByFubi;
	}

	/**
	 * 积分支付的金额
	 * @param orderPaidByFubi 积分支付的金额
	 */
	public void setOrderPaidByFubi(BigDecimal orderPaidByFubi) {
		this.orderPaidByFubi = orderPaidByFubi;
	}
	/**
	 * 是否选择了使用积分
	 * @return 是否选择了使用积分
	 */
	public Integer getUseFubi() {
		return useFubi;
	}

	/**
	 * 是否选择了使用积分
	 * @param useFubi 是否选择了使用积分
	 */
	public void setUseFubi(Integer useFubi) {
		this.useFubi = useFubi;
	}
	/**
	 * 订单已优惠金额(满立减)
	 * @return 订单已优惠金额(满立减)
	 */
	public BigDecimal getOrderPromotionDiscount() {
		return orderPromotionDiscount;
	}

	/**
	 * 订单已优惠金额(满立减)
	 * @param orderPromotionDiscount 订单已优惠金额(满立减)
	 */
	public void setOrderPromotionDiscount(BigDecimal orderPromotionDiscount) {
		this.orderPromotionDiscount = orderPromotionDiscount;
	}
	/**
	 * 订单赠送的积分
	 * @return 订单赠送的积分
	 */
	public BigDecimal getOrderGivePoints() {
		return orderGivePoints;
	}

	/**
	 * 订单赠送的积分
	 * @param orderGivePoints 订单赠送的积分
	 */
	public void setOrderGivePoints(BigDecimal orderGivePoints) {
		this.orderGivePoints = orderGivePoints;
	}
	/**
	 * 取消原因  1 未支付自动取消 2 用户主动取消 3 客服取消
	 * @return 取消原因  1 未支付自动取消 2 用户主动取消 3 客服取消
	 */
	public Integer getCancelReason() {
		return cancelReason;
	}

	/**
	 * 取消原因  1 未支付自动取消 2 用户主动取消 3 客服取消
	 * @param cancelReason 取消原因  1 未支付自动取消 2 用户主动取消 3 客服取消
	 */
	public void setCancelReason(Integer cancelReason) {
		this.cancelReason = cancelReason;
	}
	/**
	 * 取消时间
	 * @return 取消时间
	 */
	public Date getOrderCancelDate() {
		return orderCancelDate;
	}

	/**
	 * 取消时间
	 * @param orderCancelDate 取消时间
	 */
	public void setOrderCancelDate(Date orderCancelDate) {
		this.orderCancelDate = orderCancelDate;
	}
	/**
	 * 取消操作人类型：0:用户取消 1:系统取消 2:客服取消
	 * @return 取消操作人类型：0:用户取消 1:系统取消 2:客服取消
	 */
	public Integer getOrderCanceOperateType() {
		return orderCanceOperateType;
	}

	/**
	 * 取消操作人类型：0:用户取消 1:系统取消 2:客服取消
	 * @param orderCanceOperateType 取消操作人类型：0:用户取消 1:系统取消 2:客服取消
	 */
	public void setOrderCanceOperateType(Integer orderCanceOperateType) {
		this.orderCanceOperateType = orderCanceOperateType;
	}
	/**
	 * 订单渠道 : 1 IOS 2 Android 3 小程序 4 微商城 5 纯H5 6 web 7 第三方（按现有第三方依次增加）
	 * @return 订单渠道 : 1 IOS 2 Android 3 小程序 4 微商城 5 纯H5 6 web 7 第三方（按现有第三方依次增加）
	 */
	public Integer getOrderChannel() {
		return orderChannel;
	}

	/**
	 * 订单渠道 : 1 IOS 2 Android 3 小程序 4 微商城 5 纯H5 6 web 7 第三方（按现有第三方依次增加）
	 * @param orderChannel 订单渠道 : 1 IOS 2 Android 3 小程序 4 微商城 5 纯H5 6 web 7 第三方（按现有第三方依次增加）
	 */
	public void setOrderChannel(Integer orderChannel) {
		this.orderChannel = orderChannel;
	}
	/**
	 * 发票id 为空视为不开发票
	 * @return 发票id 为空视为不开发票
	 */
	public Long getInvoiceId() {
		return invoiceId;
	}

	/**
	 * 发票id 为空视为不开发票
	 * @param invoiceId 发票id 为空视为不开发票
	 */
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	/**
	 * 0：未删除1：回收站-用户可恢复到02：用户完全删除(客服可协助恢复到0或1)
	 * @return 0：未删除1：回收站-用户可恢复到02：用户完全删除(客服可协助恢复到0或1)
	 */
	public Integer getOrderDeleteStatus() {
		return orderDeleteStatus;
	}

	/**
	 * 0：未删除1：回收站-用户可恢复到02：用户完全删除(客服可协助恢复到0或1)
	 * @param orderDeleteStatus 0：未删除1：回收站-用户可恢复到02：用户完全删除(客服可协助恢复到0或1)
	 */
	public void setOrderDeleteStatus(Integer orderDeleteStatus) {
		this.orderDeleteStatus = orderDeleteStatus;
	}
	/**
	 * 0:普通订单，24小时不付款自动取消1:敏感商品订单，30分钟不付款自动取消
	 * @return 0:普通订单，24小时不付款自动取消1:敏感商品订单，30分钟不付款自动取消
	 */
	public Integer getPaidOnlineThreshold() {
		return paidOnlineThreshold;
	}

	/**
	 * 0:普通订单，24小时不付款自动取消1:敏感商品订单，30分钟不付款自动取消
	 * @param paidOnlineThreshold 0:普通订单，24小时不付款自动取消1:敏感商品订单，30分钟不付款自动取消
	 */
	public void setPaidOnlineThreshold(Integer paidOnlineThreshold) {
		this.paidOnlineThreshold = paidOnlineThreshold;
	}
	/**
	 * 收货地址id
	 * @return 收货地址id
	 */
	public Long getReceiverAddressId() {
		return receiverAddressId;
	}

	/**
	 * 收货地址id
	 * @param receiverAddressId 收货地址id
	 */
	public void setReceiverAddressId(Long receiverAddressId) {
		this.receiverAddressId = receiverAddressId;
	}
	/**
	 * 订单更新地址
	 * @return 订单更新地址
	 */
	public String getModifiedAddress() {
		return modifiedAddress;
	}

	/**
	 * 订单更新地址
	 * @param modifiedAddress 订单更新地址
	 */
	public void setModifiedAddress(String modifiedAddress) {
		this.modifiedAddress = modifiedAddress;
	}
	/**
	 * 地址更新时间
	 * @return 地址更新时间
	 */
	public Date getModifyAddressTime() {
		return modifyAddressTime;
	}

	/**
	 * 地址更新时间
	 * @param modifyAddressTime 地址更新时间
	 */
	public void setModifyAddressTime(Date modifyAddressTime) {
		this.modifyAddressTime = modifyAddressTime;
	}
	/**
	 * 增值税
	 * @return 增值税
	 */
	public BigDecimal getIncrementTaxAmount() {
		return incrementTaxAmount;
	}

	/**
	 * 增值税
	 * @param incrementTaxAmount 增值税
	 */
	public void setIncrementTaxAmount(BigDecimal incrementTaxAmount) {
		this.incrementTaxAmount = incrementTaxAmount;
	}
	/**
	 * 关税
	 * @return 关税
	 */
	public BigDecimal getCustomsDutiesAmount() {
		return customsDutiesAmount;
	}

	/**
	 * 关税
	 * @param customsDutiesAmount 关税
	 */
	public void setCustomsDutiesAmount(BigDecimal customsDutiesAmount) {
		this.customsDutiesAmount = customsDutiesAmount;
	}
	/**
	 * 订单配送完成日期
	 * @return 订单配送完成日期
	 */
	public Date getDeliveryEndtime() {
		return deliveryEndtime;
	}

	/**
	 * 订单配送完成日期
	 * @param deliveryEndtime 订单配送完成日期
	 */
	public void setDeliveryEndtime(Date deliveryEndtime) {
		this.deliveryEndtime = deliveryEndtime;
	}
	/**
	 * 发货状态，0：未发货 10：部分发货 20：已发货
	 * @return 发货状态，0：未发货 10：部分发货 20：已发货
	 */
	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	/**
	 * 发货状态，0：未发货 10：部分发货 20：已发货
	 * @param deliveryStatus 发货状态，0：未发货 10：部分发货 20：已发货
	 */
	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	/**
	 * 订单最后一次修改人(客服参与修改的话，此处为客服ID)
	 * @return 订单最后一次修改人(客服参与修改的话，此处为客服ID)
	 */
	public Long getLastOperatorId() {
		return lastOperatorId;
	}

	/**
	 * 订单最后一次修改人(客服参与修改的话，此处为客服ID)
	 * @param lastOperatorId 订单最后一次修改人(客服参与修改的话，此处为客服ID)
	 */
	public void setLastOperatorId(Long lastOperatorId) {
		this.lastOperatorId = lastOperatorId;
	}
	/**
	 * 退款时间
	 * @return 退款时间
	 */
	public Date getRefundTime() {
		return refundTime;
	}

	/**
	 * 退款时间
	 * @param refundTime 退款时间
	 */
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 订单备注
	 * @return 订单备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 订单备注
	 * @param remark 订单备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public BigDecimal getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(BigDecimal couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public RefundVo getRefundVo() {
		return refundVo;
	}

	public void setRefundVo(RefundVo refundVo) {
		this.refundVo = refundVo;
	}

	public BigDecimal getOrderPaidByJidian() {
		//不能在这里设置，否则在xml会更新成0
		//return Objects.isNull(orderPaidByJidian)?BigDecimal.ZERO:orderPaidByJidian;
		return orderPaidByJidian;
	}

	public void setOrderPaidByJidian(BigDecimal orderPaidByJidian) {
		this.orderPaidByJidian = orderPaidByJidian;
	}

	public BigDecimal getRefundJidian() {
		//不能在这里设置，否则在xml会更新成0
		//return Objects.isNull(refundJidian)?BigDecimal.ZERO:refundJidian;
		return refundJidian;
	}

	public void setRefundJidian(BigDecimal refundJidian) {
		this.refundJidian = refundJidian;
	}

	public BigDecimal getOrderCardPaid() {
		return orderCardPaid;
	}

	public void setOrderCardPaid(BigDecimal orderCardPaid) {
		this.orderCardPaid = orderCardPaid;
	}

	public BigDecimal getRefundCard() {
		return refundCard;
	}

	public void setRefundCard(BigDecimal refundCard) {
		this.refundCard = refundCard;
	}
}
