package com.egeo.components.finance.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账户明细列表和批次审核列表VO
 * @author jiang
 * @date 2018-01-06 15:25:30
 */
public class AccountBatchVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 账户id
	 */
	private Long inFlowAccountId;
	
	private Long outFlowAccountId;
	
	private String inFlowAccountName;
	
	private String outFlowAccountName;
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

	private String reason;
	private String type;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 */
	private Integer status;
	
	private Long companyId;
	
	private String companyName;
	
	private Integer flowAmount;
	
	private String createTime;
	
	private String currencyType;
	
	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getFlowAmount() {
		return flowAmount;
	}

	public void setFlowAmount(Integer flowAmount) {
		this.flowAmount = flowAmount;
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
	 * 充值/调整批次
	 * @return 充值/调整批次
	 */
	public String getRaBatch() {
		return raBatch;
	}

	/**
	 * 充值/调整批次
	 * @param raBatch 充值/调整批次
	 */
	public void setRaBatch(String raBatch) {
		this.raBatch = raBatch;
	}	
	/**
	 * 财务批次
	 * @return 财务批次
	 */
	public String getFinBatch() {
		return finBatch;
	}

	/**
	 * 财务批次
	 * @param finBatch 财务批次
	 */
	public void setFinBatch(String finBatch) {
		this.finBatch = finBatch;
	}	
	/**
	 * 金额
	 * @return 金额
	 */
	public BigDecimal getSum() {
		return sum;
	}

	/**
	 * 金额
	 * @param sum 金额
	 */
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}	
	/**
	 * 原因id
	 * @return 原因id
	 */
	public Long getReasonId() {
		return reasonId;
	}

	/**
	 * 原因id
	 * @param reasonId 原因id
	 */
	public void setReasonId(Long reasonId) {
		this.reasonId = reasonId;
	}	
	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}	
	/**
	 * 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 * @return 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 * @param status 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	public Long getInFlowAccountId() {
		return inFlowAccountId;
	}

	public void setInFlowAccountId(Long inFlowAccountId) {
		this.inFlowAccountId = inFlowAccountId;
	}

	public Long getOutFlowAccountId() {
		return outFlowAccountId;
	}

	public void setOutFlowAccountId(Long outFlowAccountId) {
		this.outFlowAccountId = outFlowAccountId;
	}

	public String getInFlowAccountName() {
		return inFlowAccountName;
	}

	public void setInFlowAccountName(String inFlowAccountName) {
		this.inFlowAccountName = inFlowAccountName;
	}

	public String getOutFlowAccountName() {
		return outFlowAccountName;
	}

	public void setOutFlowAccountName(String outFlowAccountName) {
		this.outFlowAccountName = outFlowAccountName;
	}

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
	