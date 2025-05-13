package com.egeo.components.promotion.po;


import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-06-19 14:06:21
 */
public class CouponUnitPO {


	private Date receivedTime;

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	private Long id;

	/**
	 * 优惠卷unit编号
	 */
	private String couponUnitCode;	

	/**
	 * 优惠卷id
	 */
	private Long couponId;	

	/**
	 * 优惠卷批次id
	 */
	private Long couponBatchId;	
	
	/**
	 * 订单id 
	 */
	private Long orderId;
	
	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 使用时间
	 */
	private Date usedTime;	

	/**
	 * 使用次数
	 */
	private Integer usedCount;	

	/**
	 * 有效开始时间
	 */
	private Date effectStartTime;	

	/**
	 * 有效结束时间
	 */
	private Date effectEndTime;	

	/**
	 * 优惠卷状态  0:未使用 1:已使用 2:已冻结 3:已过期 4:已失效
	 */
	private Integer couponUnitStatus;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 平台id
	 */
	private Long platformId;

	private Long batchIndex;

	private String couponBatchName;

	/**
	 * 是否已读 0-未读 1-已读
	 */
	private Integer isRead;

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public String getCouponBatchName() {
		return couponBatchName;
	}

	public void setCouponBatchName(String couponBatchName) {
		this.couponBatchName = couponBatchName;
	}

	public Long getBatchIndex() {
		return batchIndex;
	}

	public void setBatchIndex(Long batchIndex) {
		this.batchIndex = batchIndex;
	}

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
	 * 优惠卷unit编号
	 * @return 优惠卷unit编号
	 */
	public String getCouponUnitCode() {
		return couponUnitCode;
	}

	/**
	 * 优惠卷unit编号
	 * @param couponUnitCode 优惠卷unit编号
	 */
	public void setCouponUnitCode(String couponUnitCode) {
		this.couponUnitCode = couponUnitCode;
	}

	/**
	 * 优惠卷id
	 * @return 优惠卷id
	 */
	public Long getCouponId() {
		return couponId;
	}

	/**
	 * 优惠卷id
	 * @param couponId 优惠卷id
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	/**
	 * 优惠卷批次id
	 * @return 优惠卷批次id
	 */
	public Long getCouponBatchId() {
		return couponBatchId;
	}

	/**
	 * 优惠卷批次id
	 * @param couponBatchId 优惠卷批次id
	 */
	public void setCouponBatchId(Long couponBatchId) {
		this.couponBatchId = couponBatchId;
	}

	/**
	 * 用户id
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 使用时间
	 * @return 使用时间
	 */
	public Date getUsedTime() {
		return usedTime;
	}

	/**
	 * 使用时间
	 * @param usedTime 使用时间
	 */
	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	/**
	 * 使用次数
	 * @return 使用次数
	 */
	public Integer getUsedCount() {
		return usedCount;
	}

	/**
	 * 使用次数
	 * @param usedCount 使用次数
	 */
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}

	/**
	 * 有效开始时间
	 * @return 有效开始时间
	 */
	public Date getEffectStartTime() {
		return effectStartTime;
	}

	/**
	 * 有效开始时间
	 * @param effectStartTime 有效开始时间
	 */
	public void setEffectStartTime(Date effectStartTime) {
		this.effectStartTime = effectStartTime;
	}

	/**
	 * 有效结束时间
	 * @return 有效结束时间
	 */
	public Date getEffectEndTime() {
		return effectEndTime;
	}

	/**
	 * 有效结束时间
	 * @param effectEndTime 有效结束时间
	 */
	public void setEffectEndTime(Date effectEndTime) {
		this.effectEndTime = effectEndTime;
	}

	/**
	 * 优惠卷状态  0:未使用 1:已使用 2:已冻结 3:已过期 4:已失效
	 * @return 优惠卷状态  0:未使用 1:已使用 2:已冻结 3:已过期 4:已失效
	 */
	public Integer getCouponUnitStatus() {
		return couponUnitStatus;
	}

	/**
	 * 优惠卷状态  0:未使用 1:已使用 2:已冻结 3:已过期 4:已失效
	 * @param couponUnitStatus 优惠卷状态  0:未使用 1:已使用 2:已冻结 3:已过期 4:已失效
	 */
	public void setCouponUnitStatus(Integer couponUnitStatus) {
		this.couponUnitStatus = couponUnitStatus;
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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
	