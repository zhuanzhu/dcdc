package com.egeo.components.finance.bean;

import java.math.BigDecimal;
import java.util.Date;

public class AccountFlowDetailVO {

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
	private String sum;

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
	private String createTime;

	/**
	 * 更新时间
	 */
	private String updateTime;

	private String type;
	
	private Long orderId;

	private String orderCode;
	
	private Long platformId;
	
	private Integer currencyType;
	/**
	 * 是否已读 0-未读 1-已读
	 */
	private Integer isRead;

	private String enterpriseId;
	private String ledgerEnterprise;
	private String ledgerPlateform;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOutflowAccountid() {
		return outflowAccountid;
	}
	public void setOutflowAccountid(Long outflowAccountid) {
		this.outflowAccountid = outflowAccountid;
	}
	public Integer getOutflowAccounttype() {
		return outflowAccounttype;
	}
	public void setOutflowAccounttype(Integer outflowAccounttype) {
		this.outflowAccounttype = outflowAccounttype;
	}
	public Long getInflowAccountid() {
		return inflowAccountid;
	}
	public void setInflowAccountid(Long inflowAccountid) {
		this.inflowAccountid = inflowAccountid;
	}
	public Integer getInflowAccounttype() {
		return inflowAccounttype;
	}
	public void setInflowAccounttype(Integer inflowAccounttype) {
		this.inflowAccounttype = inflowAccounttype;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public Long getReasonId() {
		return reasonId;
	}
	public void setReasonId(Long reasonId) {
		this.reasonId = reasonId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public Integer getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(Integer currencyType) {
		this.currencyType = currencyType;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getLedgerEnterprise() {
		return ledgerEnterprise;
	}
	public void setLedgerEnterprise(String ledgerEnterprise) {
		this.ledgerEnterprise = ledgerEnterprise;
	}
	public String getLedgerPlateform() {
		return ledgerPlateform;
	}
	public void setLedgerPlateform(String ledgerPlateform) {
		this.ledgerPlateform = ledgerPlateform;
	}
	

}
