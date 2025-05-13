package com.egeo.components.user.controller.views.request;

public class UserRequest {

	private String productCode;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * appid
	 */
	private String appId;
	//
	/**
	 * 用户uuid
	 */
	private String userUuid;
	/**
	 * 图形验证码
	 */
	private String perCode;

	// 身份证号码
	private String idCard;
	// 开始时间
	private String startDate;
	// 结束时间
	private String endDate;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getPerCode() {
		return perCode;
	}

	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
