package com.egeo.components.user.controller.views.request;

public class VadidatePerCodeRequest {
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 图形验证码
	 */
	private String perCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPerCode() {
		return perCode;
	}

	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}

}
