package com.egeo.components.user.controller.views.request;

public class MsgRequest {
	// 手机号
	private String mobile;
	// 类型，0注册，1登录, 2 实名认证
	private String type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
