package com.egeo.components.finance.dto;

import java.io.Serializable;

public class CashFlowResultDTO implements Serializable{

	private static final long serialVersionUID = -1480581835820544264L;

	private boolean result;

	private String raBatchNo;

	private Long batchId;

	public String getRaBatchNo() {
		return raBatchNo;
	}

	public void setRaBatchNo(String raBatchNo) {
		this.raBatchNo = raBatchNo;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	//	public Long getOutFlowAccId() {
//		return outFlowAccId;
//	}
//	public void setOutFlowAccId(Long outFlowAccId) {
//		this.outFlowAccId = outFlowAccId;
//	}
//	public Integer getOutFlowAccType() {
//		return outFlowAccType;
//	}
//	public void setOutFlowAccType(Integer outFlowAccType) {
//		this.outFlowAccType = outFlowAccType;
//	}
//	public Long getInFlowAccId() {
//		return inFlowAccId;
//	}
//	public void setInFlowAccId(Long inFlowAccId) {
//		this.inFlowAccId = inFlowAccId;
//	}
//	public Integer getInFlowAccType() {
//		return inFlowAccType;
//	}
//	public void setInFlowAccType(Integer inFlowAccType) {
//		this.inFlowAccType = inFlowAccType;
//	}
//	public Double getSum() {
//		return sum;
//	}
//	public void setSum(Double sum) {
//		this.sum = sum;
//	}
//	public boolean isOutFlowCipherValid() {
//		return outFlowCipherValid;
//	}
//	public void setOutFlowCipherValid(boolean outFlowCipherValid) {
//		this.outFlowCipherValid = outFlowCipherValid;
//	}
//	public boolean isInFlowCipherValid() {
//		return inFlowCipherValid;
//	}
//	public void setInFlowCipherValid(boolean inFlowCipherValid) {
//		this.inFlowCipherValid = inFlowCipherValid;
//	}
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	
	
}
