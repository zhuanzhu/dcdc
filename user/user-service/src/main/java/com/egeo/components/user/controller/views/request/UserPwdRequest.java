package com.egeo.components.user.controller.views.request;

public class UserPwdRequest {
	// 手机号
	private String mobile;
	// 密码
	private String passwordMd5;
	// 短信验证码
	private String idfyCode;

	// 用户旧密码
	private String oldPassword;
	// 用户新密码1
	private String newPasswordOne;
	// 用户新密码2
	private String newPasswordTwo;
	/**
	 * 用户类型
	 */
	private String userType;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPasswordMd5() {
		return passwordMd5;
	}

	public void setPasswordMd5(String passwordMd5) {
		this.passwordMd5 = passwordMd5;
	}

	public String getIdfyCode() {
		return idfyCode;
	}

	public void setIdfyCode(String idfyCode) {
		this.idfyCode = idfyCode;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPasswordOne() {
		return newPasswordOne;
	}

	public void setNewPasswordOne(String newPasswordOne) {
		this.newPasswordOne = newPasswordOne;
	}

	public String getNewPasswordTwo() {
		return newPasswordTwo;
	}

	public void setNewPasswordTwo(String newPasswordTwo) {
		this.newPasswordTwo = newPasswordTwo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
