package com.egeo.components.user.controller.views.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.egeo.components.user.vo.Roles;

import io.swagger.annotations.ApiModelProperty;

public class UniAuthLoginResponse {

	private String accessToken;
	private String accessExpire;
	private String refreshToken;
	private String refreshExpire;
	private String userUuid;
	private String userId;
	private String userName;
	private Integer status;
	private String loanAidInstitutionsCode;
	@ApiModelProperty(value = "角色组集合")
	private Map<String, Set<Roles>> roleGroups = new HashMap<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessExpire() {
		return accessExpire;
	}

	public void setAccessExpire(String accessExpire) {
		this.accessExpire = accessExpire;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getRefreshExpire() {
		return refreshExpire;
	}

	public void setRefreshExpire(String refreshExpire) {
		this.refreshExpire = refreshExpire;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getLoanAidInstitutionsCode() {
		return loanAidInstitutionsCode;
	}

	public void setLoanAidInstitutionsCode(String loanAidInstitutionsCode) {
		this.loanAidInstitutionsCode = loanAidInstitutionsCode;
	}

	public Map<String, Set<Roles>> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(Map<String, Set<Roles>> roleGroups) {
		this.roleGroups = roleGroups;
	}

}
