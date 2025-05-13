package com.egeo.components.stock.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.utils.BeanUtil;

/**
 * 请求头处理类
 */
public class HeaderUtils {

	private static ThreadLocal<CommonHeader> threadLocal = new ThreadLocal<>();
	//private static final XLogger log = XLogger.getLogger(HeaderUtils.class);

	/**
	 * 获取请求头公共字段
	 *
	 * @param request
	 * @return
	 */
	public static void getHeaderPublicParam(HttpServletRequest request) {
		//log.info("获取请求头公共字段，url【{}】", request.getRequestURI());
		Enumeration<String> headerNames = request.getHeaderNames();

		Map<String, String> headMap = new HashMap<>();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			headMap.put(name, request.getHeader(name));
		}
		CommonHeader commonHeader = BeanUtil.beanCopy(headMap, CommonHeader.class);
		threadLocal.set(commonHeader);
	}

	/**
	 * 获取公共字段
	 * 
	 * @return
	 */
	public static CommonHeader getCommonHeader() {
		return threadLocal.get();
	}
}
