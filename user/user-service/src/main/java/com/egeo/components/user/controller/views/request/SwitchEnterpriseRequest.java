package com.egeo.components.user.controller.views.request;

import io.swagger.annotations.ApiModelProperty;

public class SwitchEnterpriseRequest {
	@ApiModelProperty(value = "企业编号")
	private String enterpriseCode;
	private String ip;
	private String userUuid;

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

}
