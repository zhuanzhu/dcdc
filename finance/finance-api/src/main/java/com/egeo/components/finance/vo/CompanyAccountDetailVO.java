package com.egeo.components.finance.vo;

public class CompanyAccountDetailVO {

	private Long id;
	private String name;
	private Double balance;
	private Integer type;
	private Integer disabled;
	private Long lastRecharge;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Long getLastRecharge() {
		return lastRecharge;
	}
	public void setLastRecharge(Long lastRecharge) {
		this.lastRecharge = lastRecharge;
	}
	
}
