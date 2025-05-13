package com.egeo.components.order.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import com.egeo.components.utils.HttpUtil;

public class IdCardTool {
	private static String regex18 = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
	private static String regex15 = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$";
	private static String regex = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";

	public static Boolean isMan(String idCardNo) {
		if (isValidate(idCardNo)) {
			String id17;
			if (idCardNo.length() == 15) {
				id17 = update2eighteen(idCardNo).substring(16, 17);
			} else {
				id17 = idCardNo.substring(16, 17);
			}
			// 获取性别
			if (Integer.parseInt(id17) % 2 != 0) {
				return true;
			}
			return false;
		}
		return null;
	}

	public static Boolean isFemale(String idCardNo) {
		if (isValidate(idCardNo)) {
			String id17;
			if (idCardNo.length() == 15) {
				id17 = update2eighteen(idCardNo).substring(16, 17);
			} else {
				id17 = idCardNo.substring(16, 17);
			}
			// 获取性别
			if (Integer.parseInt(id17) % 2 == 0) {
				return true;
			}
			return false;
		}
		return null;
	}

	public static String birthday(String idCardNo) {
		if (isValidate(idCardNo)) {
			String birthday = "";
			if (idCardNo.length() == 15) {
				birthday = update2eighteen(idCardNo).substring(6, 14);
			} else {
				birthday = idCardNo.substring(6, 14);
			}

			return birthday;
		}
		return null;
	}

	private static String formatDate(String str) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}

	public static Integer age(String idCardNo) {
		if (isValidate(idCardNo)) {
			String year = "";
			if (idCardNo.length() == 15) {
				year = update2eighteen(idCardNo).substring(6, 10);
			} else {
				year = idCardNo.substring(6, 10);
			}
			if (HttpUtil.CheckParamInt(year) && Integer.valueOf(year) > 0) {

				return DateUtils.nowYearInt() - Integer.valueOf(year);
			}
		}
		return 25;
	}

	/**
	 */
	public static String birthday2(String idCardNo) {
		String birthday = birthday(idCardNo);
		if (birthday != null) {
			String rslt = birthday.substring(0, 4) + "-" + birthday.substring(4, 6) + "-" + birthday.substring(6);
			return rslt;
		}
		return null;
	}

	public static boolean isValidate(String idCardNo) {
		return Pattern.matches(regex, idCardNo);
	}

	public static boolean address(String idCardNo) {
		return Pattern.matches(regex, idCardNo);
	}

	public static String update2eighteen(String code) {

		if (code == null || code.length() != 15) {
			return "";
		}

		code = code.trim();

		if (code.length() != 15 || !isValidate(code)) {
			return "";
		}

		code = code.substring(0, 6) + "19" + code.substring(6);
		//
		code = code + getCheckFlag(code);

		return code;
	}

	/**
	 * 初始化位权值
	 */
	public static int[] Wi = new int[17];

	private static void setWiBuffer() {
		for (int i = 0; i < Wi.length; i++) {
			int k = (int) Math.pow(2, (Wi.length - i));
			Wi[i] = k % 11;
		}
	}

	private static String getCheckFlag(String code) {

		int[] varArray = new int[code.length()];
		String lastNum = "";
		int numSum = 0;
		// 初始化位权值
		setWiBuffer();
		for (int i = 0; i < code.length(); i++) {
			varArray[i] = Integer.valueOf("" + code.charAt(i));
			varArray[i] = varArray[i] * Wi[i];
			numSum = numSum + varArray[i];
		}
		int checkDigit = 12 - numSum % 11;
		switch (checkDigit) {
		case 10:
			lastNum = "X";
			break;
		case 11:
			lastNum = "0";
			break;
		case 12:
			lastNum = "1";
			break;
		default:
			lastNum = String.valueOf(checkDigit);
		}
		return lastNum;
	}

	public static void main(String[] args) {
		System.out.println(age("422324831004004"));
	}

}
