package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoModifyPriceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 父item的id
	 */
	private String parentOrderCode;	

	/**
	 * 修改后订单金额(不含运费/运费险)
	 */
	private BigDecimal orderAmount;	

	/**
	 * 改价前订单金额(不含运费/运费险)
	 */
	private BigDecimal orderBeforeAmount;	

	/**
	 * 运费(实收)
	 */
	private BigDecimal orderDeliveryFee;	

	/**
	 * 改价前运费(实收)
	 */
	private BigDecimal orderBeforeDeliveryFee;	

	/**
	 * 审核状态 0:草稿 1:待审核 2:审核通过 -2:审核不通过
	 */
	private Integer auditStatus;	

	/**
	 * 提交人id
	 */
	private Long commitUserId;	

	/**
	 * 提交人id
	 */
	private String commitUserName;	

	/**
	 * 提交时间
	 */
	private Date commitTime;	

	/**
	 * 审核人id
	 */
	private Long auditUserId;	

	/**
	 * 审核人姓名
	 */
	private String auditUserName;	

	/**
	 * 审核时间
	 */
	private Date auditTime;	

	/**
	 * 审核原因
	 */
	private String auditReason;	

	/**
	 * 收货人姓名
	 */
	private String goodReceiverName;	

	/**
	 * 收货人手机
	 */
	private String goodReceiverMobile;	

	/**
	 * 收货人电话
	 */
	private String goodReceiverPhone;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 
	 */
	private Long merchantId;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

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
	/**
	 * 父item的id
	 * @return 父item的id
	 */
	public String getParentOrderCode() {
		return parentOrderCode;
	}

	/**
	 * 父item的id
	 * @param parentOrderCode 父item的id
	 */
	public void setParentOrderCode(String parentOrderCode) {
		this.parentOrderCode = parentOrderCode;
	}
	/**
	 * 修改后订单金额(不含运费/运费险)
	 * @return 修改后订单金额(不含运费/运费险)
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	/**
	 * 修改后订单金额(不含运费/运费险)
	 * @param orderAmount 修改后订单金额(不含运费/运费险)
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	/**
	 * 改价前订单金额(不含运费/运费险)
	 * @return 改价前订单金额(不含运费/运费险)
	 */
	public BigDecimal getOrderBeforeAmount() {
		return orderBeforeAmount;
	}

	/**
	 * 改价前订单金额(不含运费/运费险)
	 * @param orderBeforeAmount 改价前订单金额(不含运费/运费险)
	 */
	public void setOrderBeforeAmount(BigDecimal orderBeforeAmount) {
		this.orderBeforeAmount = orderBeforeAmount;
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
	 * 改价前运费(实收)
	 * @return 改价前运费(实收)
	 */
	public BigDecimal getOrderBeforeDeliveryFee() {
		return orderBeforeDeliveryFee;
	}

	/**
	 * 改价前运费(实收)
	 * @param orderBeforeDeliveryFee 改价前运费(实收)
	 */
	public void setOrderBeforeDeliveryFee(BigDecimal orderBeforeDeliveryFee) {
		this.orderBeforeDeliveryFee = orderBeforeDeliveryFee;
	}
	/**
	 * 审核状态 0:草稿 1:待审核 2:审核通过 -2:审核不通过
	 * @return 审核状态 0:草稿 1:待审核 2:审核通过 -2:审核不通过
	 */
	public Integer getAuditStatus() {
		return auditStatus;
	}

	/**
	 * 审核状态 0:草稿 1:待审核 2:审核通过 -2:审核不通过
	 * @param auditStatus 审核状态 0:草稿 1:待审核 2:审核通过 -2:审核不通过
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 提交人id
	 * @return 提交人id
	 */
	public Long getCommitUserId() {
		return commitUserId;
	}

	/**
	 * 提交人id
	 * @param commitUserId 提交人id
	 */
	public void setCommitUserId(Long commitUserId) {
		this.commitUserId = commitUserId;
	}
	/**
	 * 提交人id
	 * @return 提交人id
	 */
	public String getCommitUserName() {
		return commitUserName;
	}

	/**
	 * 提交人id
	 * @param commitUserName 提交人id
	 */
	public void setCommitUserName(String commitUserName) {
		this.commitUserName = commitUserName;
	}
	/**
	 * 提交时间
	 * @return 提交时间
	 */
	public Date getCommitTime() {
		return commitTime;
	}

	/**
	 * 提交时间
	 * @param commitTime 提交时间
	 */
	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}
	/**
	 * 审核人id
	 * @return 审核人id
	 */
	public Long getAuditUserId() {
		return auditUserId;
	}

	/**
	 * 审核人id
	 * @param auditUserId 审核人id
	 */
	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}
	/**
	 * 审核人姓名
	 * @return 审核人姓名
	 */
	public String getAuditUserName() {
		return auditUserName;
	}

	/**
	 * 审核人姓名
	 * @param auditUserName 审核人姓名
	 */
	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	/**
	 * 审核时间
	 * @return 审核时间
	 */
	public Date getAuditTime() {
		return auditTime;
	}

	/**
	 * 审核时间
	 * @param auditTime 审核时间
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	/**
	 * 审核原因
	 * @return 审核原因
	 */
	public String getAuditReason() {
		return auditReason;
	}

	/**
	 * 审核原因
	 * @param auditReason 审核原因
	 */
	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}
	/**
	 * 收货人姓名
	 * @return 收货人姓名
	 */
	public String getGoodReceiverName() {
		return goodReceiverName;
	}

	/**
	 * 收货人姓名
	 * @param goodReceiverName 收货人姓名
	 */
	public void setGoodReceiverName(String goodReceiverName) {
		this.goodReceiverName = goodReceiverName;
	}
	/**
	 * 收货人手机
	 * @return 收货人手机
	 */
	public String getGoodReceiverMobile() {
		return goodReceiverMobile;
	}

	/**
	 * 收货人手机
	 * @param goodReceiverMobile 收货人手机
	 */
	public void setGoodReceiverMobile(String goodReceiverMobile) {
		this.goodReceiverMobile = goodReceiverMobile;
	}
	/**
	 * 收货人电话
	 * @return 收货人电话
	 */
	public String getGoodReceiverPhone() {
		return goodReceiverPhone;
	}

	/**
	 * 收货人电话
	 * @param goodReceiverPhone 收货人电话
	 */
	public void setGoodReceiverPhone(String goodReceiverPhone) {
		this.goodReceiverPhone = goodReceiverPhone;
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
	/**
	 * 
	 * @return 
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 
	 * @param merchantId 
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
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
}
	