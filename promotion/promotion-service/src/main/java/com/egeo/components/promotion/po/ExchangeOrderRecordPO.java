package com.egeo.components.promotion.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author feng
 * @date 2018-12-14 10:57:18
 */
public class ExchangeOrderRecordPO {


	private Long id;

	/**
	 * 以旧换新活动id
	 */
	private Long exchangeId;	

	/**
	 * 以旧换新活动名称
	 */
	private String exchangeName;	

	/**
	 * 旧优惠券unit编号
	 */
	private String oldUnitCode;	

	/**
	 * 旧优惠券unit状态
	 */
	private Integer oldUnitStatus;	

	/**
	 * 旧批次优惠券名称
	 */
	private String oldCouponName;	

	/**
	 * 旧批次编号
	 */
	private String oldBatchCode;	

	/**
	 * 旧优惠卷类型 0: 满减卷 1:兑换卷
	 */
	private Integer oldCouponType;	

	/**
	 * 新的优惠券unit编号
	 */
	private String newUnitCode;	

	/**
	 * 新批次编号
	 */
	private String newBatchCode;	

	/**
	 * 新优惠券名称
	 */
	private String newCouponName;	

	/**
	 * 新优惠券类型
	 */
	private Integer newCouponType;	

	/**
	 * 用户邮箱
	 */
	private String userMail;	

	/**
	 * 兑换时间
	 */
	private Date exchangeTime;	

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 订单状态,当订单付款成功兑换完成时,设置此处状态为1(已完成)
	 */
	private Integer conversionStatus;

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 
	 */
	private Date updateTime;

	/**
	 * 平台id
	 */
	private Long platformId;

	private BigDecimal orderAmount;

	/**
	 * 批次名称
	 */
	private String oldCouponBatchName;
	private String newCouponBatchName;

	public String getOldCouponBatchName() {
		return oldCouponBatchName;
	}

	public void setOldCouponBatchName(String oldCouponBatchName) {
		this.oldCouponBatchName = oldCouponBatchName;
	}

	public String getNewCouponBatchName() {
		return newCouponBatchName;
	}

	public void setNewCouponBatchName(String newCouponBatchName) {
		this.newCouponBatchName = newCouponBatchName;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * 平台id
	 * @return
	 */
	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
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
	 * 以旧换新活动id
	 * @return 以旧换新活动id
	 */
	public Long getExchangeId() {
		return exchangeId;
	}

	/**
	 * 以旧换新活动id
	 * @param exchangeId 以旧换新活动id
	 */
	public void setExchangeId(Long exchangeId) {
		this.exchangeId = exchangeId;
	}

	/**
	 * 以旧换新活动名称
	 * @return 以旧换新活动名称
	 */
	public String getExchangeName() {
		return exchangeName;
	}

	/**
	 * 以旧换新活动名称
	 * @param exchangeName 以旧换新活动名称
	 */
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	/**
	 * 旧优惠券unit编号
	 * @return 旧优惠券unit编号
	 */
	public String getOldUnitCode() {
		return oldUnitCode;
	}

	/**
	 * 旧优惠券unit编号
	 * @param oldUnitCode 旧优惠券unit编号
	 */
	public void setOldUnitCode(String oldUnitCode) {
		this.oldUnitCode = oldUnitCode;
	}

	/**
	 * 旧优惠券unit状态
	 * @return 旧优惠券unit状态
	 */
	public Integer getOldUnitStatus() {
		return oldUnitStatus;
	}

	/**
	 * 旧优惠券unit状态
	 * @param oldUnitStatus 旧优惠券unit状态
	 */
	public void setOldUnitStatus(Integer oldUnitStatus) {
		this.oldUnitStatus = oldUnitStatus;
	}

	/**
	 * 旧批次优惠券名称
	 * @return 旧批次优惠券名称
	 */
	public String getOldCouponName() {
		return oldCouponName;
	}

	/**
	 * 旧批次优惠券名称
	 * @param oldCouponName 旧批次优惠券名称
	 */
	public void setOldCouponName(String oldCouponName) {
		this.oldCouponName = oldCouponName;
	}

	/**
	 * 旧批次编号
	 * @return 旧批次编号
	 */
	public String getOldBatchCode() {
		return oldBatchCode;
	}

	/**
	 * 旧批次编号
	 * @param oldBatchCode 旧批次编号
	 */
	public void setOldBatchCode(String oldBatchCode) {
		this.oldBatchCode = oldBatchCode;
	}

	/**
	 * 旧优惠卷类型 0: 满减卷 1:兑换卷
	 * @return 旧优惠卷类型 0: 满减卷 1:兑换卷
	 */
	public Integer getOldCouponType() {
		return oldCouponType;
	}

	/**
	 * 旧优惠卷类型 0: 满减卷 1:兑换卷
	 * @param oldCouponType 旧优惠卷类型 0: 满减卷 1:兑换卷
	 */
	public void setOldCouponType(Integer oldCouponType) {
		this.oldCouponType = oldCouponType;
	}

	/**
	 * 新的优惠券unit编号
	 * @return 新的优惠券unit编号
	 */
	public String getNewUnitCode() {
		return newUnitCode;
	}

	/**
	 * 新的优惠券unit编号
	 * @param newUnitCode 新的优惠券unit编号
	 */
	public void setNewUnitCode(String newUnitCode) {
		this.newUnitCode = newUnitCode;
	}

	/**
	 * 新批次编号
	 * @return 新批次编号
	 */
	public String getNewBatchCode() {
		return newBatchCode;
	}

	/**
	 * 新批次编号
	 * @param newBatchCode 新批次编号
	 */
	public void setNewBatchCode(String newBatchCode) {
		this.newBatchCode = newBatchCode;
	}

	/**
	 * 新优惠券名称
	 * @return 新优惠券名称
	 */
	public String getNewCouponName() {
		return newCouponName;
	}

	/**
	 * 新优惠券名称
	 * @param newCouponName 新优惠券名称
	 */
	public void setNewCouponName(String newCouponName) {
		this.newCouponName = newCouponName;
	}

	/**
	 * 新优惠券类型
	 * @return 新优惠券类型
	 */
	public Integer getNewCouponType() {
		return newCouponType;
	}

	/**
	 * 新优惠券类型
	 * @param newCouponType 新优惠券类型
	 */
	public void setNewCouponType(Integer newCouponType) {
		this.newCouponType = newCouponType;
	}

	/**
	 * 用户邮箱
	 * @return 用户邮箱
	 */
	public String getUserMail() {
		return userMail;
	}

	/**
	 * 用户邮箱
	 * @param userMail 用户邮箱
	 */
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	/**
	 * 兑换时间
	 * @return 兑换时间
	 */
	public Date getExchangeTime() {
		return exchangeTime;
	}

	/**
	 * 兑换时间
	 * @param exchangeTime 兑换时间
	 */
	public void setExchangeTime(Date exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	/**
	 * 订单编号
	 * @return 订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单编号
	 * @param orderCode 订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getConversionStatus() {
		return conversionStatus;
	}

	public void setConversionStatus(Integer conversionStatus) {
		this.conversionStatus = conversionStatus;
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
	 * 
	 * @return 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 * @param updateTime 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	