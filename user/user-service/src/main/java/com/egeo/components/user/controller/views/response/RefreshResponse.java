package com.egeo.components.user.controller.views.response;

public class RefreshResponse {

	private String accessToken;

	private String refreshToken;

	private String accessExpire;

	private String refreshExpire;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAccessExpire() {
		return accessExpire;
	}

	public void setAccessExpire(String accessExpire) {
		this.accessExpire = accessExpire;
	}

	public String getRefreshExpire() {
		return refreshExpire;
	}

	public void setRefreshExpire(String refreshExpire) {
		this.refreshExpire = refreshExpire;
	}

}
