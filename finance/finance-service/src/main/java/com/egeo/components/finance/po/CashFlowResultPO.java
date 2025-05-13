package com.egeo.components.finance.po;

public class CashFlowResultPO {

	private boolean result;

	private String raBatchNo;

	private Long batchId;
	
	

	public CashFlowResultPO() {}
	
	public CashFlowResultPO(boolean result) {
		super();
		this.result = result;
	}

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

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
