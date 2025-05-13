package com.egeo.components.order.dto;

import com.egeo.components.order.enums.ThirdConst;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jiang
 * @date 2018-01-29 09:55:24
 */
public class SoChildDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal discount;
	private Long userId;
	private Integer orderConfirmStatus;
	private Integer orderPayStatus;
	private BigDecimal thirdpartySoChildPayAmount;

	private Integer thirdpartySoChildStatus;
	private Long thirdpartySoChildId;
	private Long id;

	private String childCode;
	private Long preSell;

	private List<SkuInfoDTO> skuInfoList;


	/**
	 * 原来的子订单id
	 */
	private Long ordinaryId;

	/**
	 * 仓库id
	 */
	private Long depotId;

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
	 *
	 */
	private BigDecimal refundAmount;

	/**
	 * 订单退款方式
	 */
	private Integer refundType;

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

	/**
	 * 发票金额
	 */
	private BigDecimal invoiceAmount;

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

	/**
	 * 子订单备注
	 */
	private String remark;

	/**
	 * 平台id
	 */
	private Long platformId;

	private Long receiverAddressId;

	private String modifiedAddress;

	private Date modifyAddressTime;

	private Date lastOperateTime;

	/**
	 * 预售商品应发货截止日期
	 */
	private Date deliverEndTime;

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
	 * 订单状态
	 */
	private Integer orderStatus;

	private Date createTimeStart;

	private Date createTimeEnd;

	private Date updateTimeStart;

	private Date updateTimeEnd;

	private String orderCode;

	private Date orderPaymentConfirmDate;

	private String goodReceiverName;

	private String goodReceiverMobile;

	private String goodReceiverPhone;

	private String merchantName;

	private String platformName;

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

	//	1-平台，2-代理商  3-京东 4-蛋糕叔叔
	private Integer source;
	/**
	 * 运费券ID
	 */
	private Long deliveryCouponId;
	/**
	 * 原始运费金额,不做拆分
	 */
	private BigDecimal ordinaryDeliveryFee;
	private Long supplierId;
	private BigDecimal thirdpatyDiscountAmount;
	private String thirdpartySoChildCode;
	/**
	 * 运费退款金额
	 */
	private BigDecimal refundDeliveryFee;

	/**
	 * 扩展字段
	 */
	private String ext;

	/**
	 * 删除状态：0未删除 2已删除
	 */
	private Integer childDeleteStatus;

	public BigDecimal getThirdpartySoChildPayAmount() {
		return thirdpartySoChildPayAmount;
	}

	public void setThirdpartySoChildPayAmount(BigDecimal thirdpartySoChildPayAmount) {
		this.thirdpartySoChildPayAmount = thirdpartySoChildPayAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Integer getThirdpartySoChildStatus() {
		return thirdpartySoChildStatus;
	}

	public void setThirdpartySoChildStatus(Integer thirdpartySoChildStatus) {
		this.thirdpartySoChildStatus = thirdpartySoChildStatus;
	}

	public Long getThirdpartySoChildId() {
		return thirdpartySoChildId;
	}

	public void setThirdpartySoChildId(Long thirdpartySoChildId) {
		this.thirdpartySoChildId = thirdpartySoChildId;
	}

	public List<SkuInfoDTO> getSkuInfoList() {
		return skuInfoList;
	}

	public void setSkuInfoList(List<SkuInfoDTO> skuInfoList) {
		this.skuInfoList = skuInfoList;
	}

	public Long getPreSell() {
		return preSell;
	}

	public void setPreSell(Long preSell) {
		this.preSell = preSell;
	}



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

	public BigDecimal getStoreDiscount() {
		return storeDiscount;
	}

	public void setStoreDiscount(BigDecimal storeDiscount) {
		this.storeDiscount = storeDiscount;
	}

	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public boolean isJd() {
		return source!=null && source.intValue()==3;
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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getOrderPaymentConfirmDate() {
		return orderPaymentConfirmDate;
	}

	public void setOrderPaymentConfirmDate(Date orderPaymentConfirmDate) {
		this.orderPaymentConfirmDate = orderPaymentConfirmDate;
	}

	public String getGoodReceiverName() {
		return goodReceiverName;
	}

	public void setGoodReceiverName(String goodReceiverName) {
		this.goodReceiverName = goodReceiverName;
	}

	public String getGoodReceiverMobile() {
		return goodReceiverMobile;
	}

	public void setGoodReceiverMobile(String goodReceiverMobile) {
		this.goodReceiverMobile = goodReceiverMobile;
	}

	public String getGoodReceiverPhone() {
		return goodReceiverPhone;
	}

	public void setGoodReceiverPhone(String goodReceiverPhone) {
		this.goodReceiverPhone = goodReceiverPhone;
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

	public Date getLastOperateTime() {
		return lastOperateTime;
	}

	public void setLastOperateTime(Date lastOperateTime) {
		this.lastOperateTime = lastOperateTime;
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
	 * 仓库id
	 * @return 仓库id
	 */
	public Long getDepotId() {
		return depotId;
	}

	/**
	 * 仓库id
	 * @param depotId 仓库id
	 */
	public void setDepotId(Long depotId) {
		this.depotId = depotId;
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
	 * 子订单分配金额
	 * @return 子订单分配金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 子订单分配金额
	 * @param amount 子订单分配金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	 *
	 * @return
	 */
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	/**
	 *
	 * @param refundAmount
	 */
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	/**
	 * 订单退款方式
	 * @return 订单退款方式
	 */
	public Integer getRefundType() {
		return refundType;
	}

	/**
	 * 订单退款方式
	 * @param refundType 订单退款方式
	 */
	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
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
	 * 发票金额
	 * @return 发票金额
	 */
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	/**
	 * 发票金额
	 * @param invoiceAmount 发票金额
	 */
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
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
	 * 子订单备注
	 * @return 子订单备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 子订单备注
	 * @param remark 子订单备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
		if(Objects.equals(ThirdConst.Merchant.JD,performingParty)) {
			this.source = ThirdConst.Source.JD;
		}else if(Objects.equals(ThirdConst.Merchant.CAKE,performingParty)) {
			this.source = ThirdConst.Source.CAKE;
		}else if(Objects.equals(ThirdConst.Merchant.WORLD,performingParty)) {
			this.source = ThirdConst.Source.WORLD;
		}else if (Objects.equals(ThirdConst.Merchant.QM,performingParty)){
			this.source = ThirdConst.Source.QM;
		}
		this.performingParty = performingParty;
	}

	public Long getReceiverAddressId() {
		return receiverAddressId;
	}

	public void setReceiverAddressId(Long receiverAddressId) {
		this.receiverAddressId = receiverAddressId;
	}

	public String getModifiedAddress() {
		return modifiedAddress;
	}

	public void setModifiedAddress(String modifiedAddress) {
		this.modifiedAddress = modifiedAddress;
	}

	public Date getModifyAddressTime() {
		return modifyAddressTime;
	}

	public void setModifyAddressTime(Date modifyAddressTime) {
		this.modifyAddressTime = modifyAddressTime;
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

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(Date updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}
	public String getThirdpartySoChildCode() {
		return thirdpartySoChildCode;
	}

	public void setThirdpartySoChildCode(String thirdpartySoChildCode) {
		this.thirdpartySoChildCode = thirdpartySoChildCode;
	}

    public BigDecimal getThirdpatyDiscountAmount() {
        return thirdpatyDiscountAmount;
    }

    public void setThirdpatyDiscountAmount(BigDecimal thirdpatyDiscountAmount) {
        this.thirdpatyDiscountAmount = thirdpatyDiscountAmount;
    }

	public BigDecimal getRefundDeliveryFee() {
		return refundDeliveryFee;
	}

	public void setRefundDeliveryFee(BigDecimal refundDeliveryFee) {
		this.refundDeliveryFee = refundDeliveryFee;
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

	public static class SkuInfoDTO implements Serializable{
		private String skuId;
		private Integer num;
		private Boolean bNeedGift=false;

		public Boolean getbNeedGift() {
			return bNeedGift;
		}

		public void setbNeedGift(Boolean bNeedGift) {
			this.bNeedGift = bNeedGift;
		}

		public String getSkuId() {
			return skuId;
		}

		public void setSkuId(String skuId) {
			this.skuId = skuId;
		}

		public Integer getNum() {
			return num;
		}

		public void setNum(Integer num) {
			this.num = num;
		}
	}

	public Integer getChildDeleteStatus() {
		return childDeleteStatus;
	}

	public void setChildDeleteStatus(Integer childDeleteStatus) {
		this.childDeleteStatus = childDeleteStatus;
	}
}
