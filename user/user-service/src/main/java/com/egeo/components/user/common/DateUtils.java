/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.egeo.components.user.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.egeo.common.DateFormatPool;

//import javassist.expr.Instanceof;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM" };

	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT_POINT = "yyyy.MM.dd";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// SSS 毫秒
	public static final String DATE_TIME_FORMAT_NUM = "yyyyMMddHHmmssSSS";
	private static String ZERO = "0";

	/**
	 * 获取默认格式的当前日期,年月日
	 * 
	 * @return
	 */
	public static String getDefaultDateNow() {
		return format(DATE_FORMAT, new Date());
	}
	public static String format(String format, Date date) {
		DateFormat sdf = DateFormatPool.getDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * 获取默认格式的日期,年月日
	 * 
	 * @param date
	 * @return
	 */
	public static String getDefaultDate(Date date) {
		return format(DATE_FORMAT, date);
	}
	/**
	 * 返回指定格式的时间
	 * 
	 * @param d
	 * @param format
	 * @return
	 */
	public static String stampToDate(Date d, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String result = sdf.format(d);
		return result;
	}
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	public static String now() {
		Calendar calendar = getCalendar();
		return now(calendar);
	}

	public static String nowMonth() {
		Calendar calendar = getCalendar();
		String now = "";
		now += getYear(calendar);
		if (getMonth(calendar) < 10) {
			now += ZERO;
		}
		now += getMonth(calendar);

		return now;
	}
	public static Integer nowYearInt() {
		Calendar calendar = getCalendar();
		String now = "";
		now += getYear(calendar);
		
		return Integer.valueOf(now);
	}

	public static String nowDay() {
		Calendar calendar = getCalendar();
		String now = "";
		now += getYear(calendar);
		if (getMonth(calendar) < 10) {
			now += ZERO;
		}
		now += getMonth(calendar);

		if (getDate(calendar) < 10) {
			now += ZERO;
		}
		now += getDate(calendar);

		return now;
	}

	public static String now(Calendar calendar) {
		String now = "";
		now += getYear(calendar);
		now += "-";

		if (getMonth(calendar) < 10) {
			now += ZERO;
		}
		now += getMonth(calendar);
		now += "-";
		if (getDate(calendar) < 10) {
			now += ZERO;
		}
		now += getDate(calendar);
		now += " ";
		if (getHour(calendar) < 10) {
			now += ZERO;
		}
		now += getHour(calendar);
		now += ":";
		if (getMinute(calendar) < 10) {
			now += ZERO;
		}
		now += getMinute(calendar);
		now += ":";
		if (getSecond(calendar) < 10) {
			now += ZERO;
		}
		now += getSecond(calendar);
		return now;
	}

	private static int getYear(Calendar calendar) {
		return calendar.get(Calendar.YEAR);
	}

	private static int getMonth(Calendar calendar) {
		return calendar.get(Calendar.MONDAY) + 1;
	}

	private static int getDate(Calendar calendar) {
		return calendar.get(Calendar.DATE);
	}

	private static int getHour(Calendar calendar) {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	private static int getMinute(Calendar calendar) {
		return calendar.get(Calendar.MINUTE);
	}

	private static int getSecond(Calendar calendar) {
		return calendar.get(Calendar.SECOND);
	}

	private static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	public static String getDateTimeBefore(int before) {
		Date now = new Date();
		Date beforeDay = new Date(now.getTime()-24*3600*before*1000);
		return formatDate(beforeDay, "yyyy-MM-dd");
	}

	public static String getDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date parseDate(String str,String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	public static String formatTime(Long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

		StringBuffer sb = new StringBuffer();
		if(day > 0) {
			sb.append(day+"天");
		}
		if(hour > 0) {
			sb.append(hour+"小时");
		}
		if(minute > 0) {
			sb.append(minute+"分");
		}
		if(second > 0) {
			sb.append(second+"秒");
		}
		return sb.toString();
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}


	/**
	 * 计算从开始日期beginDate的工作天数load，的日期
	 *  例如：  beginDate 是 2015年9月30日 周三
	 *       load 2天
	 *       
	 *  结果：  2015年9月30日 周三   | 2015年10月8日 周日  [10.1-10.7]放假  [10月8日周日，但是属于工作日]
	 *  
	 * @param beginDate 开始日期
	 * @param load
	 * @return
	 */
	/*public static List<Date> workDaysFrom(Date beginDate,Integer load){
	}*/
	/*// 暂时不考虑是否节假日
	public static List<Date> workDaysFrom(Date startDay,Integer howMuchDayAfter){
		List<Date> workDays = new ArrayList<Date>();
		
		if(startDay!=null&&StringUtil.isReal(howMuchDayAfter)){

			Date startWorkDay = new Date();
			Date endWorkDay ;
			//首先判断开始时间是否是工作日，如果不是工作日，就要从下周一开始
			if(startDay.getDay()<6&&startDay.getDay()>0){//工作日
				startWorkDay = new Date(startDay.getTime()-1*24*3600*1000);
			}else if(startDay.getDay()==6){//周六
				startWorkDay = new Date(startDay.getTime()+1*24*3600*1000);
			}
			
			int weeks = (startWorkDay.getDay()+howMuchDayAfter)/5;
			int leftDay = (startWorkDay.getDay()+howMuchDayAfter)%5;
			endWorkDay = new Date(startWorkDay.getTime()+weeks*7*24*3600*1000L+leftDay*24*3600*1000-startWorkDay.getDay()*24*3600*1000-((leftDay==0&&weeks>0)?2*24*3600*1000:0));
			
			// 
			Date fromDate = new Date(isSameDay(startWorkDay, startDay)? startWorkDay.getTime() : startDay.getTime());
			Date tempWorkDay = new Date(fromDate.getTime());
			while(!tempWorkDay.before(fromDate) && !tempWorkDay.after(endWorkDay)) {
				//如果是工作日
				if(tempWorkDay.getDay() > 0 && tempWorkDay.getDay() < 6 ){
					Date workDay = new Date(tempWorkDay.getTime());
					workDays.add(workDay);
				}
				// 日期往后推延
				tempWorkDay.setTime(addDays(tempWorkDay,1).getTime());
			}
			return workDays;
		}
		
		return workDays;
	}
	
	*//**
	 * 从当天开始多少的工作日，如果当天是工作日，那么也计数
	 * @param startDay  开始时间
	 * @param howMuchDayAfter  多少工作日之后
	 * @return
	 *//*
	@SuppressWarnings("deprecation")
	public static Date workDayAfter(Date startDay,Integer howMuchDayAfter){
		if(startDay!=null&&StringUtil.isReal(howMuchDayAfter)){

			Date startWorkDay = new Date();
			Date endWorkDay ;
			//首先判断开始时间是否是工作日，如果不是工作日，就要从下周一开始
			if(startDay.getDay()<6&&startDay.getDay()>0){//工作日
				startWorkDay = new Date(startDay.getTime()-1*24*3600*1000);
			}else if(startDay.getDay()==6){//周六
				startWorkDay = new Date(startDay.getTime()+1*24*3600*1000);
			}
			int weeks = (startWorkDay.getDay()+howMuchDayAfter)/5;
			int leftDay = (startWorkDay.getDay()+howMuchDayAfter)%5;
			// bug: 7*24*3600*1000很容易变成负数
			endWorkDay = new Date(startWorkDay.getTime()+weeks*7*24*3600*1000L+leftDay*24*3600*1000-startWorkDay.getDay()*24*3600*1000-((leftDay==0&&weeks>0)?2*24*3600*1000:0));
			return endWorkDay;
		}
		
		return null;
	}*/
	public static Date nextWorkDay(Date today){
		if(today!=null){
			if(today.getDay()<5&&today.getDay()>=0){//工作日
				return  new Date(today.getTime()+1*24*3600*1000);
			}else if(today.getDay()==6){//周六
				return new Date(today.getTime()+2*24*3600*1000);
			}else if(today.getDay()==5){//周五
				return new Date(today.getTime()+3*24*3600*1000);
			}
		}
		
		return null;
	}
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		//System.out.println(workDayAfter(parseDate("2015-12-04"), 30));
		//System.out.println(parseDate("2015-12-04"));
		//System.out.println(new Date(parseDate("2015-12-04").getTime() +  30 *24* 3600*1000));
		//System.out.println(new Date((new Date()).getTime()-4*24*3600*1000));
		System.out.println(DateUtils.getDateTimeBefore(1));
	}
	
	public static Calendar getFirstDayOfMonth(Date paramDate){
		if(paramDate == null){ // 日期不存在，返回空
			return null;
		}
		
		// 入参 所对应的参数
		Calendar paramDateCalendar = Calendar.getInstance();
		paramDateCalendar.setTime(paramDate);
		
		// 对应月份的一号
		Calendar firstDayOfMonth =  Calendar.getInstance();
		firstDayOfMonth.set(Calendar.YEAR,paramDateCalendar.get(Calendar.YEAR));
		firstDayOfMonth.set(Calendar.MONTH,paramDateCalendar.get(Calendar.MONTH));
		firstDayOfMonth.set(Calendar.DATE,1);
		firstDayOfMonth.set(Calendar.HOUR_OF_DAY,0);
		firstDayOfMonth.set(Calendar.MINUTE,0);
		firstDayOfMonth.set(Calendar.SECOND,0);
		firstDayOfMonth.set(Calendar.MILLISECOND,0);
		return firstDayOfMonth;
	}
	/**
	 * 计算出之前的第一个周日，如果当天是周日，就给出当天
	 * @param date
	 * @return
	 */
	public static Calendar getFirstSunDay(Calendar paramDate){
		if(paramDate == null){ // 日期不存在，返回空
			return null;
		}
		
		
		Calendar firstSunday =  Calendar.getInstance();
		
		
		// 月份的第一个星期天 是月份
			// 提前 -1 ×
			// 周几 -1
		firstSunday.setTime(addDays(paramDate.getTime(),-1 * (paramDate.get(Calendar.DAY_OF_WEEK) - 1)));
		
		return firstSunday;
	}
	/**
	 * 计算出一个日期  所在月份的 一号之前的第一个周日
	 *   例如， 入参 -- 12月25日
	 *        12月1日，之前的第一个周日 [如果12月1日是周日，则返回12月1日]
	 * @param date
	 * @return
	 */
	public static Date getFirstSundayOfMonth(Date paramDate){
		if(paramDate == null){ // 日期不存在，返回空
			return null;
		}
		
		/*// 入参 所对应的参数
		Calendar paramDateCalendar = Calendar.getInstance();
		paramDateCalendar.setTime(paramDate);
		
		Calendar firstSunday =  Calendar.getInstance();*/
		
		// 对应月份的一号
		Calendar firstDayOfMonth =  getFirstDayOfMonth(paramDate);
		
		// 月份的第一个星期天 是月份
			// 提前 -1 ×
			// 周几 -1
		Calendar firstSunday = getFirstSunDay(firstDayOfMonth);
		
		return firstSunday.getTime();
	}
	/**
	 * 获取一个日期相隔几天是哪一天
	 * @param initDate yyyy-MM-dd
	 * @param intervalDays 
	 * @return yyyy-MM-dd
	 */
	public static String getIntervalDate(String initDate,int intervalDays){
		String result = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = dateFormat.parse(initDate);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, intervalDays);
			date = calendar.getTime();
			result = dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}
