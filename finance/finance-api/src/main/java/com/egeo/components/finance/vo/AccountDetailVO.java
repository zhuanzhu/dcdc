package com.egeo.components.finance.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author jiang
 * @date 2018-01-06 15:25:30
 */
public class AccountDetailVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
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
	private String type;
	private String reason;
	
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
	private String createTime;

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
	 * 流水数量
	 * @return 流水数量
	 */
	public Integer getFlowAmount() {
		return flowAmount;
	}

	/**
	 * 流水数量
	 * @param flowAmount 流水数量
	 */
	public void setFlowAmount(Integer flowAmount) {
		this.flowAmount = flowAmount;
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
	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}	
}
	