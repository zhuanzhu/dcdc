package com.egeo.components.finance.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-06 15:24:25
 */
public class AccountBatchPO {

	private Long id;

	private Long outflowAccountid;
	
	private Long inflowAccountid;

	/**
	 * 充值/调整批次
	 */
	private String raBatch;

	/**
	 * 财务批次
	 */
	private String finBatch;

	/**
	 * 金额
	 */
	private BigDecimal sum;

	/**
	 * 原因id
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
	 * 执行人id
	 */
	private Long operatorId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private Integer type;

	private Long companyId;
	
	private Long platformId;
	/**
	 * 货币类型 0:积分 1:点赞福豆 2:现金 3:多种类型
	 */
	private Integer currencyType;

	public Integer getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(Integer currencyType) {
		this.currencyType = currencyType;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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


	public Long getOutflowAccountid() {
		return outflowAccountid;
	}

	public void setOutflowAccountid(Long outflowAccountid) {
		this.outflowAccountid = outflowAccountid;
	}

	public Long getInflowAccountid() {
		return inflowAccountid;
	}

	public void setInflowAccountid(Long inflowAccountid) {
		this.inflowAccountid = inflowAccountid;
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
	 * 原因id
	 * 
	 * @return 原因id
	 */
	public Long getReasonId() {
		return reasonId;
	}

	/**
	 * 原因id
	 * 
	 * @param reasonId
	 *            原因id
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
	 * 执行人id
	 * 
	 * @return 执行人id
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * 执行人id
	 * 
	 * @param operatorId
	 *            执行人id
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
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
