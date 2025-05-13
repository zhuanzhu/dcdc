package com.egeo.components.user.controller.views.request;

public class UserTokenRequest {
	// 刷新tokenID
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
