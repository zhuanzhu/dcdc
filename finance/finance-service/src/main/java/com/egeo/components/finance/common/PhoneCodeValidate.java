package com.egeo.components.finance.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.egeo.utils.log.XLogger;

public class PhoneCodeValidate {
	private static final XLogger logger = XLogger.getLogger(PhoneCodeValidate.class);

	public static boolean validatePhoneCode(String str) {
		// String regExp = "^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])\\d{8}$";
		String regExp = "^(\\+?0?86)?((13[0-9])|(14[5-9])|(15[0-3,5-9])|(16[1,5-7])|(17[0-8])|(18[0-9])|191|198|199)\\d{8}$";
		String strNew = str.replaceAll("-", "").replaceAll(" ", "");
		System.out.println(strNew);
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(strNew);
		return m.matches();
	}

	/**
	 * @Description 根据正则匹配手机号码
	 * @param str
	 * @param regex
	 * @return
	 * @author wangweijian
	 * @time 2019年5月28日 下午3:40:00
	 */
	public static boolean validatePhoneCode(String str, String regex) {
		String strNew = str.replaceAll("-", "").replaceAll(" ", "");
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(strNew);
		boolean flag = m.matches();
		logger.info("原号码：{}，修正后号码：{}，是否为手机号码：{}", str, strNew, flag);
		return flag;
	}
}
