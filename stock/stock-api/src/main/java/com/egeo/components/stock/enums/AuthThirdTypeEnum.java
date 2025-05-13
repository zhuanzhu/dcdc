package com.egeo.components.stock.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2017-2019, 仁辉科技有限公司
 *
 * @Author: chenhui Date: 11:42 2019/9/24 Description: History: Version: 1.0
 */
public enum AuthThirdTypeEnum {

	AUTH_WFSS(1, "违法涉诉","wfssInfo",20), 
	AUTH_SDXD(2, "涉毒吸毒在逃前科","sdxdInfo",30),
	AUTH_SMRZ(3, "实名认证","",10),
	AUTH_NIANLING(4, "年龄校验","ageInfo",40),
	AUTH_TSMD(5, "金融高风险认证","tsmdInfo",50),
	AUTH_DUOTOU(6, "非银机构多头","duotouInfo",60),
	AUTH_ZWSC(7, "手机号码在网时长","zwscInfo",70),
	AUTH_MOBILE_REALNAME(8, "手机实名认证","mrealInfo",80);

	private static Map<Integer, AuthThirdTypeEnum> enumMap = new HashMap<>();

	static {
		for (AuthThirdTypeEnum authThirdTypeEnum : AuthThirdTypeEnum.values()) {
			enumMap.put(authThirdTypeEnum.getCode(), authThirdTypeEnum);
		} 
	}

	AuthThirdTypeEnum(int code, String name,String authKey,int loanCode) {
		this.code = code;
		this.loanCode = loanCode;
		this.name = name;
		this.authKey = authKey;
	}

	public static AuthThirdTypeEnum getByCode(Integer code) {
		return enumMap.get(code);
	}

	private int code;
	private int loanCode;
	private String name;
	private String authKey;

	public int getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(int loanCode) {
		this.loanCode = loanCode;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
