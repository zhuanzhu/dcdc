package com.egeo.components.user.controller.views.request;

public class UserBussTypeRequest {

	private String bussType;
	/**
	 * 用户uuid
	 */
	private String userUuid;

	/**
	 * 助贷机构
	 */
	private String loanAid;


	public String getBussType() {
		return bussType;
	}

	public void setBussType(String bussType) {
		this.bussType = bussType;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getLoanAid() {
		return loanAid;
	}

	public void setLoanAid(String loanAid) {
		this.loanAid = loanAid;
	}
}
