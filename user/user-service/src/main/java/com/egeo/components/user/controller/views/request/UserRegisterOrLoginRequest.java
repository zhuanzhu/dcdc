package com.egeo.components.user.controller.views.request;

public class UserRegisterOrLoginRequest {
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 密码
	 */
	private String passwordMd5;

	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 短信验证码
	 */
	private String idfyCode;
	/**
	 * 用户类型
	 */
	private String userType;
	private String userUnique;

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

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getIdfyCode() {
		return idfyCode;
	}

	public void setIdfyCode(String idfyCode) {
		this.idfyCode = idfyCode;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserUnique() {
		return userUnique;
	}

	public void setUserUnique(String userUnique) {
		this.userUnique = userUnique;
	}

}
