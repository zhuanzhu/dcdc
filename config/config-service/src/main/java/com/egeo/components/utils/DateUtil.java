package com.egeo.components.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Created by guofeng.qin on 2017/4/7 0007.
 */
public class DateUtil {
	private static Logger logger = Logger.getLogger(DateUtil.class);
	/**
	 * yyyyMMdd
	 */
	public static final String DateDay = "yyyyMMdd";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DateDayTime = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String DateDayTimeSeril = "yyyyMMddHHmmss";
	/**
	 * YYYY-MM-DD
	 */
	public static final String YearMonthDay = "yyy-MM-dd";
	
	/**
	 * yyyy
	 */
	public static final String YYYY = "yyyy";
	
	/**
	 * MM
	 */
	public static final String MM = "MM";
	
	/**
	 * dd
	 */
	public static final String DD = "dd";
    public static String dateToStamp(String s) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date date = simpleDateFormat.parse(s);
        return String.valueOf(date.getTime());
    }

	public static String formatDate(String format, Date time) {
		if(time==null){
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(time);
	}
    public static String stampToDate(long stamp)
    {
        Date date = new Date(stamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return simpleDateFormat.format(date);
    }

    public static String stampToDateSec(long stamp)
    {
        Date date = new Date(stamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
    
    public static String stampToDateDay(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }
    
    public static String stampToDateMonth(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        return simpleDateFormat.format(date);
    }
    /**
     * 时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author zhou_k 20170510
     */
    public static int getDaySub(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
       return Integer.parseInt(String.valueOf(between_days));           
    }   
    public static boolean getDateMm(Date need){
		logger.info("最后申请日期:"+need);
		Date currentDate = new Date();// 当前日期
		SimpleDateFormat sft = new SimpleDateFormat("yyyyMMdd");// 日期格式化
		logger.info("====:"+sft.format(need).equals(sft.format(currentDate))); // 两个格式化的字符串比较
    	return sft.format(need).equals(sft.format(currentDate));
    }
    public static void main(String[] args) throws ParseException {
    	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String date = "2017-05-28 19:30:46";
		System.out.println(getDateMm(sdf.parse(date)));
		
	}
    
    /**
     * 比较日期大小
     */
    public static int compareDate(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    
    public static String getCurrentDate() throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }
	public static String parseDateToString(Date date, String format) throws ParseException {
		if (null == date) {
			return null;
		}
		SimpleDateFormat parser = new SimpleDateFormat(format);
		return parser.format(date);
	}
    public static Date parseDate(String format, String timeStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
            return null;
        }

    }
    public static String addYearStringDay(Date time) {
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time);
            calendar.add(Calendar.YEAR, 100);
            return formatDate(DateDay, calendar.getTime());
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 计算当前时间加上特定时间，返回时间戳
     * @param tokenExpire
     * @return
     */
    public static long getSecondTimestampTwo(long tokenExpire){  
        long currentTime = new Date().getTime();
        currentTime += tokenExpire;
        return currentTime/1000;  
    }
    
    public static int getTwoTimeSub(long sTime,long bTime){
    	long phoneDay =(sTime-bTime)/(24*60*60*1000);
    	return (int)phoneDay;
    }
}	