package com.egeo.components.finance.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-06 15:24:25
 */
public class AccountDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 操作批次id,与account_batch表id关联
	 */
	private Long batchId;

	/**
	 * 充值/调整批次
	 */
	private String raBatch;

	/**
	 * 财务批次
	 */
	private String finBatch;

	/**
	 * 流水id
	 */
	private Long flowId;

	/**
	 * 账户id
	 */
	private Long accountId;

	/**
	 * 金额
	 */
	private BigDecimal sum;

	/**
	 * 操作原因id
	 */
	private Long reasonId;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 流水数量
	 */
	private Integer flowAmount;

	/**
	 * 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private Long platformId;

	private Integer type;
	
	private Long orderId;
	
	private String orderCode;
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	 * 主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 操作批次id,与account_batch表id关联
	 * 
	 * @return 操作批次id,与account_batch表id关联
	 */
	public Long getBatchId() {
		return batchId;
	}

	/**
	 * 操作批次id,与account_batch表id关联
	 * 
	 * @param batchId
	 *            操作批次id,与account_batch表id关联
	 */
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	/**
	 * 充值/调整批次
	 * 
	 * @return 充值/调整批次
	 */
	public String getRaBatch() {
		return raBatch;
	}

	/**
	 * 充值/调整批次
	 * 
	 * @param raBatch
	 *            充值/调整批次
	 */
	public void setRaBatch(String raBatch) {
		this.raBatch = raBatch;
	}

	/**
	 * 财务批次
	 * 
	 * @return 财务批次
	 */
	public String getFinBatch() {
		return finBatch;
	}

	/**
	 * 财务批次
	 * 
	 * @param finBatch
	 *            财务批次
	 */
	public void setFinBatch(String finBatch) {
		this.finBatch = finBatch;
	}

	/**
	 * 流水id
	 * 
	 * @return 流水id
	 */
	public Long getFlowId() {
		return flowId;
	}

	/**
	 * 流水id
	 * 
	 * @param flowId
	 *            流水id
	 */
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	/**
	 * 账户id
	 * 
	 * @return 账户id
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * 账户id
	 * 
	 * @param accountId
	 *            账户id
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * 金额
	 * 
	 * @return 金额
	 */
	public BigDecimal getSum() {
		return sum;
	}

	/**
	 * 金额
	 * 
	 * @param sum
	 *            金额
	 */
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	/**
	 * 操作原因id
	 * 
	 * @return 操作原因id
	 */
	public Long getReasonId() {
		return reasonId;
	}

	/**
	 * 操作原因id
	 * 
	 * @param reasonId
	 *            操作原因id
	 */
	public void setReasonId(Long reasonId) {
		this.reasonId = reasonId;
	}

	/**
	 * 备注
	 * 
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * 
	 * @param remark
	 *            备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 流水数量
	 * 
	 * @return 流水数量
	 */
	public Integer getFlowAmount() {
		return flowAmount;
	}

	/**
	 * 流水数量
	 * 
	 * @param flowAmount
	 *            流水数量
	 */
	public void setFlowAmount(Integer flowAmount) {
		this.flowAmount = flowAmount;
	}

	/**
	 * 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 * 
	 * @return 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 * 
	 * @param status
	 *            状态 0:待审核 1:已通过 2:未通过 3:已完成
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
}
