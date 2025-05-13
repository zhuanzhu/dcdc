package com.egeo.components.finance.vo;

import java.math.BigDecimal;

/**
 * 客户端账户余额VO
 * @author GRACIA
 *
 */
public class AccountBalanceVO {

	private Long accountId;
	private String accountName;
	private BigDecimal balance;
	private Integer detail=1;
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Integer getDetail() {
		return detail;
	}
	public void setDetail(Integer detail) {
		this.detail = detail;
	}
	
	
}
