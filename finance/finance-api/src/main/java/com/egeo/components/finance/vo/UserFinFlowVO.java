package com.egeo.components.finance.vo;

/**
 * 后台用户资产流水列表VO
 * 
 * @author graci
 *
 */
public class UserFinFlowVO {

	private String name;
	private String userCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	private String email;
	private String companyName;

	/**
	 * 流水id
	 */
	private Long flowId;
	
	private String outFlowAccount;

	private String inFlowAccount;

	private Double sum;

	private String createTime;

	private String flowType;

	private String reason;

	private String remark;
	
	private String type;
	private String currencyType;
	
	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public String getOutFlowAccount() {
		return outFlowAccount;
	}

	public void setOutFlowAccount(String outFlowAccount) {
		this.outFlowAccount = outFlowAccount;
	}

	public String getInFlowAccount() {
		return inFlowAccount;
	}

	public void setInFlowAccount(String inFlowAccount) {
		this.inFlowAccount = inFlowAccount;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
