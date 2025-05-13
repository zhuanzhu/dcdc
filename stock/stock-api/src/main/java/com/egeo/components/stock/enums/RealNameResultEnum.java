package com.egeo.components.stock.enums;

import java.util.HashMap;
import java.util.Map;

public enum RealNameResultEnum {
	NO_AUTH(0, "未实名"),
	AUTH_PASS(1, "验证通过"), 
	AUTH_REJECT(2, "验证不通过"), 
	PIC_NOT_CLEAR(3, "图片不清晰"), 
	EXCEPTION(4, "异常");

	private static Map<Integer, RealNameResultEnum> enumMap = new HashMap<>();

	static {
		for (RealNameResultEnum ealNameResult : RealNameResultEnum.values()) {
			enumMap.put(ealNameResult.getCode(), ealNameResult);
		}
	}

	public static RealNameResultEnum getByCode(Integer code) {
		return enumMap.get(code);
	}

	RealNameResultEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private int code;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
