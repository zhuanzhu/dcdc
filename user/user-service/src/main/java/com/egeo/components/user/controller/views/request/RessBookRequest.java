package com.egeo.components.user.controller.views.request;

import io.swagger.annotations.ApiModelProperty;

public class RessBookRequest {
	@ApiModelProperty(value = "通讯录详情")
	private String ressBook;
	@ApiModelProperty(value = "用户uuid")
	private String userUuid;
	@ApiModelProperty(value = "手机号")
	private String mobile;
	@ApiModelProperty(value = "0 登录时 ,1验证时")
	private String type;

	public String getRessBook() {
		return ressBook;
	}

	public void setRessBook(String ressBook) {
		this.ressBook = ressBook;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
