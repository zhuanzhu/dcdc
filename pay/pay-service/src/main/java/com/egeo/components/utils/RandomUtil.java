package com.egeo.components.utils;

import java.math.BigDecimal;
import java.util.Random;

public class RandomUtil {
		
	/**
	* 创建指定数量的随机字符串
	* @param   numberFlag 是否是数字
	* @param   length
	* @author  zhou_k 2017-4-24
	* @return
	*/
	public static String createRandom(boolean numberFlag, int length){
	 String retStr = "";
	 String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
	 int len = strTable.length();
	 boolean bDone = true;
	 do {
		  retStr = "";
		  int count = 0;
		  for (int i = 0; i < length; i++) {
		  double dblR = Math.random() * len;
		  int intR = (int) Math.floor(dblR);
		  char c = strTable.charAt(intR);
		  if (('0' <= c) && (c <= '9')) {
		   count++;
		  }
		  retStr += strTable.charAt(intR);
		  }
		  if (count >= 2) {
		  bDone = false;
	    }
	 } while (bDone);
	    return retStr;
	}
	/**
	 * 随机生成1-10000数
	 * @return
	 */
	public static int randomId(){
		int max=10000;
		int min=1;
		Random random = new Random();
		int sum = random.nextInt(max)%(max-min+1) + min;
		return sum;
	}
	/**
     * 元转换为内部存储单位（万分之一分)
     */
    public static long yuanToInner(double num) {
        return (long) (num * 1000000);
    }

    /**
     * 内部存储单位(万分之一分)转为元
     */
    public static double innerToYuan(long num) {
        double yuan = num / 1000000.00D;
        BigDecimal big = new BigDecimal(yuan);
        double finalYuan = big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return finalYuan;
    }
    
    public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
    
}
