package com.egeo.components.utils;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.egeo.utils.StringUtils;

/**
 * @author guofeng.qin
 */
public class StringUtil {
	public static char[] NUMBER_CHAR = "零壹贰叁肆伍陆柒捌玖".toCharArray();

	public static char[] UNIT_CHAR = "元拾佰仟万拾佰仟亿拾佰仟".toCharArray();

	private static final Logger log = Logger.getLogger(StringUtil.class);

	/**
	 * 对字符串处理:将指定位置到指定位置的字符以星号代替
	 * 
	 * @param content 传入的字符串
	 * @param begin   开始位置
	 * @param end     结束位置
	 * @return
	 */
	public static String getStarString(String content, int begin, int end) {

		if (begin >= content.length() || begin < 0) {
			return content;
		}
		if (end >= content.length() || end < 0) {
			return content;
		}
		if (begin >= end) {
			return content;
		}
		String starStr = "";
		for (int i = begin; i < end; i++) {
			starStr = starStr + "*";
		}
		return content.substring(0, begin) + starStr + content.substring(end, content.length());

	}

	/**
	 * 首字母变小写
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 首字母变大写
	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 对字符加星号处理：除前面几位和后面几位外，其他的字符以星号代替
	 * 
	 * @param content  传入的字符串
	 * @param frontNum 保留前面字符的位数
	 * @param endNum   保留后面字符的位数
	 * @return 带星号的字符串
	 */

	public static String getEndString(String content, int frontNum, int endNum) {

		if (frontNum >= content.length() || frontNum < 0) {
			return content;
		}
		if (endNum >= content.length() || endNum < 0) {
			return content;
		}
		if (frontNum + endNum >= content.length()) {
			return content;
		}
		String starStr = "";
		for (int i = 0; i < (content.length() - frontNum - endNum); i++) {
			starStr = starStr + "*";
		}
		return content.substring(0, frontNum) + starStr
				+ content.substring(content.length() - endNum, content.length());

	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param String s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
		}
		return len;
	}

	/**
	 * 将小写金额转换成大写金额(以元为单位)
	 * 
	 * @param amount 小写金额
	 * @return 大写金额
	 */
	public static String amountRMB(int amount) {

		StringBuffer retSb = new StringBuffer();
		String retStr = "";
		int length = String.valueOf(amount).length() - 1;// 长度
		int pos = length;// 当前位置
		int posValue = 0;// 当前位置对应的值
		int dividend = (int) Math.pow(10, length);// 被除数
		boolean flag = false;

		if (amount < 10) {
			retSb.append(NUMBER_CHAR[amount]);
			retSb.append(UNIT_CHAR[pos]);
		} else {
			while (pos > 0) {
				posValue = amount / dividend;
				amount = amount % dividend;
				if (posValue > 0) {
					if (flag && (pos != 3 && pos != 7)) {
						retSb.append(NUMBER_CHAR[0]);
					}
					flag = false;
					retSb.append(NUMBER_CHAR[posValue]);
					retSb.append(UNIT_CHAR[pos]);
				}
				if (posValue == 0) {
					flag = true;
					if (pos == 4 || pos == 8) {
						retSb.append(UNIT_CHAR[pos]);
					}
				}
				pos--;
				dividend = dividend / 10;
			}
			if (amount > 0) {
				if (flag && (pos != 3 && pos != 7)) {
					retSb.append(NUMBER_CHAR[0]);
				}
				retSb.append(NUMBER_CHAR[amount]);
			}
			retSb.append(UNIT_CHAR[0]);
		}
		retSb.append("整");

		retStr = retSb.toString().replaceAll("亿万", "亿");

		System.out.println(retStr);
		return retStr;
	}

	private static final ThreadLocal<StringBuilderHolder> threadLocalStringBuilderHolder = new ThreadLocal<StringBuilderHolder>() {
		protected StringBuilderHolder initialValue() {
			return new StringBuilderHolder(256);
		}
	};

	public static String concat(Object... strs) {
		StringBuilder sb = threadLocalStringBuilderHolder.get().resetAndGetStringBuilder();
		for (Object str : strs) {
			sb.append(str);
		}
		return sb.toString();
	}

	public static String join(String split, String... objs) {
		StringBuilder sb = threadLocalStringBuilderHolder.get().resetAndGetStringBuilder();
		int len = objs.length;
		for (int index = 0; index < len; index++) {
			if (index > 0) {
				sb.append(split);
			}
			sb.append(objs[index]);
		}
		return sb.toString();
	}

	public static String format(String template, Object... objs) {
		return String.format(template, objs);
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() <= 0;
	}

	public static String getMD5(String plain) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");

			md5.update((plain).getBytes("UTF-8"));
			byte b[] = md5.digest();

			int i;
			StringBuffer buf = new StringBuffer("");

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}

			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static StringBuilder getStringBuilder() {
		return threadLocalStringBuilderHolder.get().resetAndGetStringBuilder();
	}

	private static class StringBuilderHolder {
		private final StringBuilder sb;

		public StringBuilderHolder(int capacity) {
			sb = new StringBuilder(capacity);
		}

		public StringBuilder resetAndGetStringBuilder() {
			sb.setLength(0);
			return sb;
		}
	}

	public static List<String> matchStr(String str, String reg) {
		List<String> list = new ArrayList<>();

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			for (int i = 1; i <= matcher.groupCount(); i++) {
				list.add(matcher.group(i));
			}
		}

		return list;
	}

	public static String matchSingleStr(String str, String reg) {
		List<String> list = matchStr(str, reg);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public static Double parseDouble(String str) {
		if (str == null) {
			return null;
		}
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			log.error("解析 Double 失败：{}" + str);
			log.error("解析 Double 失败!!!" + e);
		}

		return null;
	}

	public static Integer parseInteger(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			log.error("解析 Integer 失败：{}" + str);
			log.error("解析 Integer 失败!!!" + e);
		}

		return null;
	}

	public static Long parseLong(String str) {
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			log.error("解析 Long 失败：{}" + str);
			log.error("解析 Long 失败!!!" + e);
		}

		return null;
	}

	public static <T> T parseXml(String xml, Class<T> clazz) {
		if (xml != null && xml.length() > 0) {
			try {
				JAXBContext ctx = JAXBContext.newInstance(clazz);
				Unmarshaller unmarshaller = ctx.createUnmarshaller();
				T result = (T) unmarshaller.unmarshal(new StringReader(xml));

				return result;
			} catch (JAXBException e) {
				log.error("Xml Parse Error: {} -- {}" + clazz.getName() + xml);
				log.error("Xml Parse Error !!!", e);
			}
		}

		return null;
	}

	/**
	 * 加法hash
	 * 
	 * @param key
	 * @param prime
	 * @return
	 */
	public static int additiveHash(String key, int prime) {
		int hash, i;
		for (hash = key.length(), i = 0; i < key.length(); i++)
			hash += key.charAt(i);
		return Math.abs((hash % prime));
	}

	public static String toTrim(String param) {
		return param.trim();
	}

	public static String formatPhone(String str) {
		StringBuilder phone = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				phone.append(str.substring(i, i + 1));
			}
		}
		return phone.toString();
	}

	/**
	 * 判断数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 检查所有的参数
	 * 
	 * @param methodName
	 * @param value
	 * @return
	 */
	public static boolean checkStringIsPass(String methodName, String... value) {
		int count = 0;
		for (int i = 0; i < value.length; i++) {
			// 遍历字符数组所有的参数，发现某个为 null 或者 "" ,则跳出
			if (StringUtils.isEmpty(value[i])) {
				log.info(methodName + "所需要的参数不完整！");
				return false;
			}
			count++;
		}
		if (count == value.length) {
			return true;
		}
		return false;
	}

	public static BigDecimal nullToZero(BigDecimal bigDecimal){
		return Objects.isNull(bigDecimal)?BigDecimal.ZERO:bigDecimal;
	}
}
