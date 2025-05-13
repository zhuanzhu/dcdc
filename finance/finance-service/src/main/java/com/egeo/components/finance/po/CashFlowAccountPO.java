package com.egeo.components.finance.po;

import java.math.BigDecimal;

public class CashFlowAccountPO {

	private Long accountId;

	private String salt;

	private BigDecimal sum;

	private BigDecimal balance;// 账户余额(删除)

	private String subRemark;// 子备注，用于记录员工充值导入时的每行备注

	private Integer userAccountType;

	public Integer getUserAccountType() {
		return userAccountType;
	}

	public void setUserAccountType(Integer userAccountType) {
		this.userAccountType = userAccountType;
	}

	public String getSubRemark() {
		return subRemark;
	}

	public void setSubRemark(String subRemark) {
		this.subRemark = subRemark;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
