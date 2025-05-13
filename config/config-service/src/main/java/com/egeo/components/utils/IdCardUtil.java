package com.egeo.components.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class IdCardUtil {
	
	final static Map<Integer, String> zoneNum = new HashMap<Integer, String>();
    static {
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "宁夏");
        zoneNum.put(65, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "外国");
    }
     
    final static int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    final static int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 
        5, 8, 4, 2};
     
    /**
     * 身份证验证
     *@param s  号码内容
     *@return 是否有效 null和"" 都是false 
     */
    public static boolean isIDCard(String certNo){
        if(certNo == null || (certNo.length() != 15 && certNo.length() != 18)){
            return false;
        }
        final char[] cs = certNo.toUpperCase().toCharArray();
        //校验位数
        int power = 0;
        for(int i=0; i<cs.length; i++){
            if(i==cs.length-1 && cs[i] == 'X')
                break;//最后一位可以 是X或x
            if(cs[i]<'0' || cs[i]>'9'){
                return false;
            }
            if(i < cs.length -1){
                power += (cs[i] - '0') * POWER_LIST[i];
            }
        }
         
        //校验区位码
        if(!zoneNum.containsKey(Integer.valueOf(certNo.substring(0,2)))){
            return false;
        }
         
        //校验年份
        String year = certNo.length() == 15 ? getIdcardCalendar() + certNo.substring(6,8) :certNo.substring(6, 10);
         
        final int iyear = Integer.parseInt(year);
        if(iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR)){
            return false;//1900年的PASS，超过今年的PASS
        }
        //校验月份
        String month = certNo.length() == 15 ? certNo.substring(8, 10) : certNo.substring(10,12);
        final int imonth = Integer.parseInt(month);
        if(imonth <1 || imonth >12){
            return false;
        }
         
        //校验天数      
        String day = certNo.length() ==15 ? certNo.substring(10, 12) : certNo.substring(12, 14);
        final int iday = Integer.parseInt(day);
        if(iday < 1 || iday > 31){
            return false;       
        } 
        //校验"校验码"
        if(certNo.length() == 15){
            return true;
        }
        return cs[cs.length -1 ] == PARITYBIT[power % 11];
    }
     
    private static int getIdcardCalendar() {        
         GregorianCalendar curDay = new GregorianCalendar();
         int curYear = curDay.get(Calendar.YEAR);
         int year2bit = Integer.parseInt(String.valueOf(curYear).substring(2));          
         return  year2bit;
    }


    /**
     * 检验名字
     *
     * @param name
     * @return
     */
    public static boolean isLegalName(String name) {
        if (name.contains("·") || name.contains("•")) {
            if (name.matches("^[\\u4e00-\\u9fa5]+[·•][\\u4e00-\\u9fa5]+$")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (name.matches("^[\\u4e00-\\u9fa5]+$")) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static String getAge(String certNo) {
        if (certNo.length() == 15) {
            return getAgeByIdCard15(certNo);
        } else {
            return getAgeByIdCard18(certNo);
        }
    }

    public static  String getSex(String certNo) {
        if (certNo.length() == 15) {
            return getSexByIdCard15(certNo);
        } else {
            return getSexByIdCard18(certNo);
        }
    }

    /**
     * 18位身份证
     * 获取 性别
     *
     * @param certNo
     * @return
     */
    private static String getSexByIdCard18(String certNo) {
        String sex = "未知"; //未知
        if (Integer.parseInt(certNo.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
            sex = "女"; //女
        } else {
            sex = "男"; //男
        }
        return sex;
    }

    /**
     * 18位身份证
     * 获取 年龄
     *
     * @param certNo
     * @return
     */
    private static String getAgeByIdCard18(String certNo) {

        //身份证上的年月日
        String idyear = certNo.substring(6).substring(0, 4);// 得到年份
        String idyue = certNo.substring(10).substring(0, 2);// 得到月份
        String idday = certNo.substring(12).substring(0, 2);//得到日
        String idyr = idyue + idday + "";

        //当前年月日
        String year = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(0, 4);// 当前年份
        String yue = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(5, 7);// 月份
        String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(8, 10);
        String yr = yue + day + "";

        int age = 0;
        if (Integer.parseInt(idyr) <= Integer.parseInt(yr)) { // 表示生日已过
            age = Integer.parseInt(year) - Integer.parseInt(idyear) + 1;
        } else {// 生日未过
            age = Integer.parseInt(year) - Integer.parseInt(idyear);
        }
        return age + "";
    }

    /**
     * 15位身份证
     * 获取 生日
     *
     * @param certNo
     * @return
     */
    public static String getBirthdayByIdCard15(String certNo) {
        //身份证上的年月日
        String idyear = "19" + certNo.substring(6, 8);
        String idyue = certNo.substring(8, 10);
        String idday = certNo.substring(10, 12);
        String bir = idyear + "-" + idyue + "-" + idday;
        return bir;
    }

    /**
     * 15位身份证
     * 获取 性别
     *
     * @param certNo
     * @return
     */
    private static String getSexByIdCard15(String certNo) {
        String sex = "未知"; //未知
        if (Integer.parseInt(certNo.substring(14, 15)) % 2 == 0) {// 判断性别
            sex = "女"; //女
        } else {
            sex = "男"; //男
        }
        return sex;
    }

    /**
     * 15位身份证
     * 获取 年龄
     *
     * @param certNo
     * @return
     */
    private static String getAgeByIdCard15(String certNo) {
        //身份证上的年月日
        String idyear = "19" + certNo.substring(6, 8);
        String idyue = certNo.substring(8, 10);
        String idday = certNo.substring(10, 12);
        String idyr = idyue + idday + "";
        //当前年月日
        String year = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(0, 4);// 当前年份
        String yue = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(5, 7);// 月份
        String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(8, 10);
        String yr = yue + day + "";
        int age = 0;
        if (Integer.parseInt(idyr) <= Integer.parseInt(yr)) { // 表示生日已过
            age = Integer.parseInt(year) - Integer.parseInt(idyear) + 1;
        } else {// 生日未过
            age = Integer.parseInt(year) - Integer.parseInt(idyear);
        }
        return age + "";
    }
	
}
