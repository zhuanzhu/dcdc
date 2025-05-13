package com.egeo.components.third.common;


import com.egeo.utils.EmptyUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paul on 2018/8/29.
 */
public class Cheker {
    /**
     * 商品比价反馈doc 文案
     */
/*    public static final String FEED_BACK_DOC = "比价说明:福管家平台内所有商品的价格均低于天猫/京东上的同类商品(" +
            "仅限于天猫官方旗舰店、京东自营、京东官方旗舰店中的商品);如果您在使用过程中发现大厨管家的商品价格高于" +
            "天猫/京东,可通过此页面反馈给我们。";*/

    /**
     * checkParam(判断数据类型)
     * @param str
     * @param type (1:正整数 , 2:字符串, 3:含有0的正整数 , 4:负整数 ,5:字母、数字 )
     * @return
     */
    public static boolean checkParam(String str, String type){
        if (EmptyUtil.isEmpty(str)) {
            return false;
        }
        String eL = "";
        if("1".equals(type)){
            eL = "^\\d*[1-9]\\d*$";			//正整数
        }else if("2".equals(type)){
            eL = "^[A-Za-z]+$";				//字符串
        }else if("3".equals(type)){
            eL = "^\\d*[0-9]\\d*$";			//0的正整数
        }else if("4".equals(type)){
            eL = "^-\\d*[1-9]\\d*$";		//负整数
        }else if("5".equals(type)){
            eL = "^[a-zA-Z0-9]+$";			//字母、数字
        }
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}

