package com.egeo.components.finance.dto;

import java.util.List;

public class UnifiedCashFlowDTO {

	private List<CashFlowAccountDTO> outFlowAccs;
	private Integer outFlowAccType;
	private List<CashFlowAccountDTO> inFlowAccs;
	private Integer inFlowAccType;
	private boolean nonNegLimit;
	private Long platformId;
	private Integer type;
	private Long orderId;
	private String orderCode;
	private Long operatorId;
	private int finBatchType;
	private Long reasonId;
	private String remark;
	private boolean isRecharge;
	private Integer operateType;
	
	public UnifiedCashFlowDTO() {}
	public UnifiedCashFlowDTO(
			List<CashFlowAccountDTO> outFlowAccs, Integer outFlowAccType,
			List<CashFlowAccountDTO> inFlowAccs, Integer inFlowAccType, 
			boolean nonNegLimit, Long platformId,
			Integer type, Long orderId, String orderCode, Long operatorId, int finBatchType, Long reasonId,
			String remark, boolean isRecharge, Integer operateType) {

		this.outFlowAccs = outFlowAccs; 
		this.outFlowAccType = outFlowAccType; 
		this.inFlowAccs = inFlowAccs;  
		this.inFlowAccType = inFlowAccType;  
		this.nonNegLimit = nonNegLimit;  
		this.platformId = platformId; 
		this.type = type;  
		this.orderId = orderId;  
		this.orderCode = orderCode;  
		this.operatorId = operatorId;  
		this.finBatchType = finBatchType;  
		this.reasonId = reasonId; 
		this.remark = remark; 
		this.isRecharge = isRecharge;  
		this.operateType = operateType; 
	}
	
	public List<CashFlowAccountDTO> getOutFlowAccs() {
		return outFlowAccs;
	}
	public void setOutFlowAccs(List<CashFlowAccountDTO> outFlowAccs) {
		this.outFlowAccs = outFlowAccs;
	}
	public Integer getOutFlowAccType() {
		return outFlowAccType;
	}
	public void setOutFlowAccType(Integer outFlowAccType) {
		this.outFlowAccType = outFlowAccType;
	}
	public List<CashFlowAccountDTO> getInFlowAccs() {
		return inFlowAccs;
	}
	public void setInFlowAccs(List<CashFlowAccountDTO> inFlowAccs) {
		this.inFlowAccs = inFlowAccs;
	}
	public Integer getInFlowAccType() {
		return inFlowAccType;
	}
	public void setInFlowAccType(Integer inFlowAccType) {
		this.inFlowAccType = inFlowAccType;
	}
	public boolean isNonNegLimit() {
		return nonNegLimit;
	}
	public boolean getNonNegLimit() {
		return nonNegLimit;
	}
	public void setNonNegLimit(boolean nonNegLimit) {
		this.nonNegLimit = nonNegLimit;
	}
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
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
	public Long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	public int getFinBatchType() {
		return finBatchType;
	}
	public void setFinBatchType(int finBatchType) {
		this.finBatchType = finBatchType;
	}
	public Long getReasonId() {
		return reasonId;
	}
	public void setReasonId(Long reasonId) {
		this.reasonId = reasonId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean isRecharge() {
		return isRecharge;
	}
	public void setRecharge(boolean isRecharge) {
		this.isRecharge = isRecharge;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
}
