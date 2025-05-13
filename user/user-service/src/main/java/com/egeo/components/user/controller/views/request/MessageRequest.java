package com.egeo.components.user.controller.views.request;

import io.swagger.annotations.ApiModelProperty;

public class MessageRequest {
	@ApiModelProperty(value = "短信详情")
	private String messageMo;
	@ApiModelProperty(value = "用户uuid")
	private String userUuid;
	@ApiModelProperty(value = "手机号")
	private String mobile;

	public String getMessageMo() {
		return messageMo;
	}

	public void setMessageMo(String messageMo) {
		this.messageMo = messageMo;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
