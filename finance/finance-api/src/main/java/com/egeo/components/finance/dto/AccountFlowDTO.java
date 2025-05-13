package com.egeo.components.finance.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-06 15:24:25
 */
public class AccountFlowDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 流出账户id
	 */
	private Long outflowAccountid;

	/**
	 * 流出账户类型 0:公司账户 1:员工账户
	 */
	private Integer outflowAccounttype;

	/**
	 * 流入账户id
	 */
	private Long inflowAccountid;

	/**
	 * 流入账户类型 0:公司账户 1:员工账户
	 */
	private Integer inflowAccounttype;

	/**
	 * 金额
	 */
	private BigDecimal sum;

	/**
	 * 操作原因id
	 */
	private Long reasonId;

	/**
	 * 原因
	 */
	private String reason;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 批次id 与account_batch表id关联
	 */
	private Long batchId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private Integer type;
	
	private Long orderId;

	private String orderCode;
	
	private Long platformId;
	
	private Integer currencyType;
	/**
	 * 是否已读 0-未读 1-已读
	 */
	private Integer isRead;

	private Long companyId;
	private Long enterpriseId;
	private BigDecimal ledgerEnterprise;
	private BigDecimal ledgerPlateform;
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public BigDecimal getLedgerEnterprise() {
		return ledgerEnterprise;
	}

	public void setLedgerEnterprise(BigDecimal ledgerEnterprise) {
		this.ledgerEnterprise = ledgerEnterprise;
	}

	public BigDecimal getLedgerPlateform() {
		return ledgerPlateform;
	}

	public void setLedgerPlateform(BigDecimal ledgerPlateform) {
		this.ledgerPlateform = ledgerPlateform;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	/**
	 * 流出账户id
	 * 
	 * @return 流出账户id
	 */
	public Long getOutflowAccountid() {
		return outflowAccountid;
	}

	/**
	 * 流出账户id
	 * 
	 * @param outflowAccountid
	 *            流出账户id
	 */
	public void setOutflowAccountid(Long outflowAccountid) {
		this.outflowAccountid = outflowAccountid;
	}

	/**
	 * 流出账户类型 0:公司账户 1:员工账户
	 * 
	 * @return 流出账户类型 0:公司账户 1:员工账户
	 */
	public Integer getOutflowAccounttype() {
		return outflowAccounttype;
	}

	/**
	 * 流出账户类型 0:公司账户 1:员工账户
	 * 
	 * @param outflowAccounttype
	 *            流出账户类型 0:公司账户 1:员工账户
	 */
	public void setOutflowAccounttype(Integer outflowAccounttype) {
		this.outflowAccounttype = outflowAccounttype;
	}

	/**
	 * 流入账户id
	 * 
	 * @return 流入账户id
	 */
	public Long getInflowAccountid() {
		return inflowAccountid;
	}

	/**
	 * 流入账户id
	 * 
	 * @param inflowAccountid
	 *            流入账户id
	 */
	public void setInflowAccountid(Long inflowAccountid) {
		this.inflowAccountid = inflowAccountid;
	}

	/**
	 * 流入账户类型 0:公司账户 1:员工账户
	 * 
	 * @return 流入账户类型 0:公司账户 1:员工账户
	 */
	public Integer getInflowAccounttype() {
		return inflowAccounttype;
	}

	/**
	 * 流入账户类型 0:公司账户 1:员工账户
	 * 
	 * @param inflowAccounttype
	 *            流入账户类型 0:公司账户 1:员工账户
	 */
	public void setInflowAccounttype(Integer inflowAccounttype) {
		this.inflowAccounttype = inflowAccounttype;
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
	 * 批次id 与account_batch表id关联
	 * 
	 * @return 批次id 与account_batch表id关联
	 */
	public Long getBatchId() {
		return batchId;
	}

	/**
	 * 批次id 与account_batch表id关联
	 * 
	 * @param batchId
	 *            批次id 与account_batch表id关联
	 */
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
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
