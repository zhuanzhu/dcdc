package com.egeo.components.finance.vo;

import java.math.BigDecimal;

/**
 * 后台用户账户列表VO
 * 
 * @author GRACIA
 *
 */
public class UserAccountVO {


	private String name;

	private String memberCode;

	private String email;

	private String companyName;

	/**
	 * 积分账户
	 */
	private double fbAcc;

	/**
	 * 点赞福豆账户
	 */
	private double ppAcc;

	/**
	 * 积分冻结账户
	 */
	private double fbfAcc;
	
	/**
	 * 现金账户
	 */
	private double cAcc;

	private Long userId;

	/**
	 * 企业失效前金额
	 */
	private BigDecimal beforeDisableBalance;


	public BigDecimal getBeforeDisableBalance() {
		return beforeDisableBalance;
	}

	public void setBeforeDisableBalance(BigDecimal beforeDisableBalance) {
		this.beforeDisableBalance = beforeDisableBalance;
	}

	public double getcAcc() {
		return cAcc;
	}

	public void setcAcc(double cAcc) {
		this.cAcc = cAcc;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
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

	public double getFbAcc() {
		return fbAcc;
	}

	public void setFbAcc(double fbAcc) {
		this.fbAcc = fbAcc;
	}

	public double getPpAcc() {
		return ppAcc;
	}

	public void setPpAcc(double ppAcc) {
		this.ppAcc = ppAcc;
	}

	public double getFbfAcc() {
		return fbfAcc;
	}

	public void setFbfAcc(double fbfAcc) {
		this.fbfAcc = fbfAcc;
	}

}
