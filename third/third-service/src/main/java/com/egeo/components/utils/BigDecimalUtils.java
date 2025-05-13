package com.egeo.components.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

	 /**金额为分的格式 */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";


    /**
	 * 元转分
	 * @param amount
	 * @return
	 */
	public static String rmbConvertYoF(String amount){
		return new BigDecimal(amount).multiply(new BigDecimal(100)).toString();
	}

	/**
	 * 分转元
	 * @param amount
	 * @return
	 */
	public static String rmbConvertFToY(String amount){
		return new BigDecimal(amount).divide(new BigDecimal(100)).toString();
	}




	public static void main(String[] args) {

		try {
			System.out.println(rmbConvertYoF("0"));
			//System.out.println(rmbConvertFToY("3525"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
