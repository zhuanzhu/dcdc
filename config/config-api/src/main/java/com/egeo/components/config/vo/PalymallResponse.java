package com.egeo.components.config.vo;

import java.util.HashMap;
import java.util.Map;

public class PalymallResponse {

	/**
	 * 系统错误
	 */
	public static final String RSP_SYSTEM_ERROR = "GSE.SYSTEM_ERROR";
	/**
	 * 验签失败
	 */
	public static final String RSP_VERIFYSIGN_FAILURE = "GSE.VERIFYSIGN_FAILURE";
	/**
	 * 未知的请求方法
	 */
	public static final String RSP_ILLEGAL_ACCESS = "GSE.ILLEGAL_ACCESS";
	/**
	 * 缺少参数
	 */
	public static final String RSP_MISS_PARAMETER = "GSE.MISS_PARAMETER";
	/**
	 * 非法参数
	 */
	public static final String RSP_INVALID_PARAMETER = "GSE.INVALID_PARAMETER";
	/**
	 * 获取ERP密钥失败
	 */
	public static final String RSP_APPKEY_FAILURE = "GSE.APPKEY_FAILURE";
	/**
	 * 平台不支持此接口
	 */
	public static final String RSP_PLAT_NOT_SUPPORT = "GSE.PLAT_NOT_SUPPORT";
	/**
	 * 平台业务错误
	 */
	public static final String RSP_LOGIC_ERROR = "GSE.LOGIC_ERROR";
	
	public static Map<String, Object> buildSuccessResponse(Map<String, Object> data) {
		return buildResponse(10000, "Success", "", "", data);
	}
	
	public static Map<String, Object> buildResponse(Integer code, String message, Map<String, Object> data) {
		return buildResponse(code, message, null, null, data);
	}
	
	public static Map<String, Object> buildResponse(Integer code, String message, String subcode, String submessage) {
		return buildResponse(code, message, subcode, submessage, null);
	}
	
	public static Map<String, Object> buildResponse(Integer code, String message, 
			String subcode, String submessage, Map<String, Object> data) {
		Map<String, Object> response = null;
		if (data != null) {
			response = data;
		} else {
			response = new HashMap<>();
		}
		response.put("code", code);
		response.put("message", message);
		response.put("subcode", subcode);
		response.put("submessage", submessage);
		return response;
	}
	
}
