package com.egeo.components.user.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class UserDataPermission {
	private String userUuid;
	private int type;
	private int state;
	private List<String> codes;
	private String codesStr;

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<String> getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = StringUtils.isNotBlank(codes) ? Arrays.asList(codes.split(",")) : new ArrayList<String>();
	}

	public void setCodesByList(List<String> codes) {
		this.codes = codes;
	}

	public String getCodesStr() {
		return codesStr;
	}

	public void setCodesStr(String codesStr) {
		this.codesStr = codesStr;
	}

}
