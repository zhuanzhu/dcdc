package com.egeo.components.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ArrayUtils;

import com.egeo.utils.log.XLogger;

public class HttpUtil {
	private static final XLogger logger = XLogger.getLogger(HttpUtil.class);

	public static boolean CheckParamString(String param) {
		if (param == null || param.trim().length() == 0) {
			// Log.info("CheckParamString " + param + " is null or length == 0");
			return false;
		}

		return true;
	}

	public static String ParseParamString(String param) {
		return param.trim();
	}

	public static boolean CheckParamDouble(String param) {
		if (param == null || param.length() == 0) {
			// Log.info("CheckParamInt " + param + " is null or length == 0");
			return false;
		}

		try {
			Double.parseDouble(param);
		} catch (NumberFormatException e) {
			// Log.info("CheckParamInt " + param + " exception");
			return false;
		}

		return true;
	}

	public static boolean CheckParamInt(String param) {
		if (param == null || param.length() == 0) {
			// Log.info("CheckParamInt " + param + " is null or length == 0");
			return false;
		}

		try {
			Integer.parseInt(param);
		} catch (NumberFormatException e) {
			// Log.info("CheckParamInt " + param + " exception");
			return false;
		}

		return true;
	}

	public static int ParseParamInt(String param) {
		return Integer.parseInt(param);
	}

	public static double ParseParamDouble(String param) {
		return Double.parseDouble(param);
	}

	public static HttpSession RegenerateSession(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		if (!httpSession.isNew()) {
			httpSession.invalidate();
			httpSession = req.getSession();
		}

		return httpSession;
	}

	public static Map<String, String> toMap(String url) {

		String strAllParam = null;
		String[] arrSplit = null;
		arrSplit = url.split("[?]");
		if (url.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}

		Map<String, String> map = null;
		if (url != null && strAllParam.indexOf("&") > -1 && strAllParam.indexOf("=") > -1) {
			map = new HashMap<String, String>();
			String[] arrTemp = strAllParam.split("&");
			for (String str : arrTemp) {
				String[] qs = str.split("=");
				map.put(qs[0], qs[1]);
			}
		}
		return map;
	}

	/**
	 * @Description 打印方法参数值 基本类型直接打印，非基本类型需要重写toString方法
	 * @param methodName方法名
	 * @param paramsArgsName  方法参数名数组
	 * @param paramsArgsValue 方法参数值数组
	 * @author wangweijian
	 * @time 2019年4月20日 下午3:02:32
	 */
	public static void logParam(String methodName, String[] paramsArgsName, Object[] paramsArgsValue) {
		try {
			if (ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)) {
				logger.info("该方法【{}】没有参数", methodName);
				return;
			}
			StringBuffer buffer = new StringBuffer();
			buffer.append("方法名：" + methodName);
			for (int i = 0; i < paramsArgsName.length; i++) {
				// 参数名
				String name = paramsArgsName[i];
				// 参数值
				Object value = paramsArgsValue[i];
				buffer.append("," + name + "=" + value);
			}
			logger.info(buffer.toString());
		} catch (Exception e) {
			logger.info("参数输出错误：", e);
		}
	}

	/**
	 * urlEncode
	 * @param str
	 * @param charset
	 * @return
	 */
	public static String urlEncode(String str,String charset){
		try {
			str=URLEncoder.encode(str,charset);
		}catch (Exception e){
			logger.info("encode异常：", e);
		}
		return str;
	}

	/**
	 * urlDecode
	 * @param str
	 * @param charset
	 * @return
	 */
	public static String urlDecode(String str,String charset){
		try {
			str= URLDecoder.decode(str,charset);
		}catch (Exception e){
			logger.info("decode异常：", e);
		}
		return str;
	}


}
