package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author wyy
 * @date 2018-06-19 14:06:21
 */
public class CouponUnitDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer getType;
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
	public Integer getGetType() {
		return getType;
	}

	public void setGetType(Integer getType) {
		this.getType = getType;
	}

	//couponunit二维码连接
	private String unitUrl;
	private Date receivedTime;

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	//活动名
	private String campaignName;
	//渠道名
	private String channelName;
	private String campaignCode;
	
	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	public String getUnitUrl() {
		return unitUrl;
	}

	public void setUnitUrl(String unitUrl) {
		this.unitUrl = unitUrl;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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
	private String effectStartTimeStr;

	/**
	 * 有效结束时间
	 */
	private Date effectEndTime;
	private String effectEndTimeStr;

	/**
	 * 优惠券批次名称
	 */
	private String couponBatchName;

	public String getCouponBatchName() {
		return couponBatchName;
	}

	public void setCouponBatchName(String couponBatchName) {
		this.couponBatchName = couponBatchName;
	}

	public String getEffectStartTimeStr() {
		return effectStartTimeStr;
	}

	public void setEffectStartTimeStr(String effectStartTimeStr) {
		this.effectStartTimeStr = effectStartTimeStr;
	}

	public String getEffectEndTimeStr() {
		return effectEndTimeStr;
	}

	public void setEffectEndTimeStr(String effectEndTimeStr) {
		this.effectEndTimeStr = effectEndTimeStr;
	}

	/**
	 * 优惠卷状态 0:未使用 1:已使用 2:已冻结 3:已过期 4:已失效
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

	private String couponCode;
	private String couponBatchCode;
	private Integer couponType;	// 优惠卷类型 0: 满减卷 1:兑换卷
	private String title;
	private Integer grantType;

	// 客户端拓展信息
	private BigDecimal discountAmount;
	private BigDecimal triggerAmount;
	private String iconUrl;
	private String detail;
	private Date couponBatchEffectStartTime;	
	private Date couponBatchEffectEndTime;	
	private Integer grantCount;
	private Integer isRepeat;
	private Integer jumpType;	// 跳转类型  1:单个SU 2:SU组；3:内部链接
	private Integer goodsType;	// 关联商品类型  0: 单su 1:商品组
	private Long goodsId;
	private Integer couponRelType;	// 优惠卷批次的类型   0:优惠卷  1:优惠卷分组
	private Long couponRelId;
	private Long companyId;
	private Long storeId;
	
	/**
	 * 客户端Id
	 */
	private Long clientId;
	
	private List<Long> storeIds;
	
	private Integer effectDays;
	private Date couponBatchReceiveStartTime;
	private Date couponBatchReceiveEndTime;
	private Long batchIndex;

	public Long getBatchIndex() {
		return batchIndex;
	}

	public void setBatchIndex(Long batchIndex) {
		this.batchIndex = batchIndex;
	}

	public Date getCouponBatchReceiveStartTime() {
		return couponBatchReceiveStartTime;
	}

	public void setCouponBatchReceiveStartTime(Date couponBatchReceiveStartTime) {
		this.couponBatchReceiveStartTime = couponBatchReceiveStartTime;
	}

	public Date getCouponBatchReceiveEndTime() {
		return couponBatchReceiveEndTime;
	}

	public void setCouponBatchReceiveEndTime(Date couponBatchReceiveEndTime) {
		this.couponBatchReceiveEndTime = couponBatchReceiveEndTime;
	}

	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getCouponRelType() {
		return couponRelType;
	}
	public void setCouponRelType(Integer couponRelType) {
		this.couponRelType = couponRelType;
	}
	public Long getCouponRelId() {
		return couponRelId;
	}
	public void setCouponRelId(Long couponRelId) {
		this.couponRelId = couponRelId;
	}
	
	public Date getCouponBatchEffectStartTime() {
		return couponBatchEffectStartTime;
	}
	public void setCouponBatchEffectStartTime(Date couponBatchEffectStartTime) {
		this.couponBatchEffectStartTime = couponBatchEffectStartTime;
	}
	public Date getCouponBatchEffectEndTime() {
		return couponBatchEffectEndTime;
	}
	public void setCouponBatchEffectEndTime(Date couponBatchEffectEndTime) {
		this.couponBatchEffectEndTime = couponBatchEffectEndTime;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getTriggerAmount() {
		return triggerAmount;
	}

	public void setTriggerAmount(BigDecimal triggerAmount) {
		this.triggerAmount = triggerAmount;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponBatchCode() {
		return couponBatchCode;
	}

	public void setCouponBatchCode(String couponBatchCode) {
		this.couponBatchCode = couponBatchCode;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
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
	 * 
	 * @return 优惠卷unit编号
	 */
	public String getCouponUnitCode() {
		return couponUnitCode;
	}

	/**
	 * 优惠卷unit编号
	 * 
	 * @param couponUnitCode
	 *            优惠卷unit编号
	 */
	public void setCouponUnitCode(String couponUnitCode) {
		this.couponUnitCode = couponUnitCode;
	}

	/**
	 * 优惠卷id
	 * 
	 * @return 优惠卷id
	 */
	public Long getCouponId() {
		return couponId;
	}

	/**
	 * 优惠卷id
	 * 
	 * @param couponId
	 *            优惠卷id
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	/**
	 * 优惠卷批次id
	 * 
	 * @return 优惠卷批次id
	 */
	public Long getCouponBatchId() {
		return couponBatchId;
	}

	/**
	 * 优惠卷批次id
	 * 
	 * @param couponBatchId
	 *            优惠卷批次id
	 */
	public void setCouponBatchId(Long couponBatchId) {
		this.couponBatchId = couponBatchId;
	}

	/**
	 * 用户id
	 * 
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * 
	 * @param userId
	 *            用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 使用时间
	 * 
	 * @return 使用时间
	 */
	public Date getUsedTime() {
		return usedTime;
	}

	/**
	 * 使用时间
	 * 
	 * @param usedTime
	 *            使用时间
	 */
	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	/**
	 * 使用次数
	 * 
	 * @return 使用次数
	 */
	public Integer getUsedCount() {
		return usedCount;
	}

	/**
	 * 使用次数
	 * 
	 * @param usedCount
	 *            使用次数
	 */
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}

	/**
	 * 有效开始时间
	 * 
	 * @return 有效开始时间
	 */
	public Date getEffectStartTime() {
		return effectStartTime;
	}

	/**
	 * 有效开始时间
	 * 
	 * @param effectStartTime
	 *            有效开始时间
	 */
	public void setEffectStartTime(Date effectStartTime) {
		this.effectStartTime = effectStartTime;
	}

	/**
	 * 有效结束时间
	 * 
	 * @return 有效结束时间
	 */
	public Date getEffectEndTime() {
		return effectEndTime;
	}

	/**
	 * 有效结束时间
	 * 
	 * @param effectEndTime
	 *            有效结束时间
	 */
	public void setEffectEndTime(Date effectEndTime) {
		this.effectEndTime = effectEndTime;
	}

	/**
	 * 优惠卷状态 0:未使用 1:已使用 2:已冻结 3:已过期 4:已失效
	 * 
	 * @return 优惠卷状态 0:未使用 1:已使用 2:已冻结 3:已过期 4:已失效
	 */
	public Integer getCouponUnitStatus() {
		return couponUnitStatus;
	}

	/**
	 * 优惠卷状态 0:未使用 1:已使用 2:已冻结 3:已过期 4:已失效
	 * 
	 * @param couponUnitStatus
	 *            优惠卷状态 0:未使用 1:已使用 2:已冻结 3:已过期 4:已失效
	 */
	public void setCouponUnitStatus(Integer couponUnitStatus) {
		this.couponUnitStatus = couponUnitStatus;
	}

	/**
	 * 创建时间
	 * 
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * 
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateTime
	 *            更新时间
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getGrantType() {
		return grantType;
	}

	public void setGrantType(Integer grantType) {
		this.grantType = grantType;
	}
	public Integer getGrantCount() {
		return grantCount;
	}
	public void setGrantCount(Integer grantCount) {
		this.grantCount = grantCount;
	}
	public Integer getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(Integer isRepeat) {
		this.isRepeat = isRepeat;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Integer getJumpType() {
		return jumpType;
	}
	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}
	public Integer getEffectDays() {
		return effectDays;
	}
	public void setEffectDays(Integer effectDays) {
		this.effectDays = effectDays;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public List<Long> getStoreIds() {
		return storeIds;
	}
	public void setStoreIds(List<Long> storeIds) {
		this.storeIds = storeIds;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
}
