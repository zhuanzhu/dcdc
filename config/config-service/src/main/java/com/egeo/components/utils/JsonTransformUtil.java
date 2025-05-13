package com.egeo.components.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * @author liwenshuai
 * @title
 * @package com.egeo.components.utils
 * @date 2019/7/18 10:38
 */
public class JsonTransformUtil {
	/**
	 * 兼容企业信息
	 *
	 * @param jsonData
	 * @return
	 */
	public static String enterpriseInfo(String jsonData) {
		String enterpriseInfoJson = jsonData.replace("\"taxpayerIdentityNumber\"", "\"taxpayer_identity_number\"")
				.replace("\"registeredCapital\"", "\"registered_capital\"").replace("\"province\"", "\"province_code\"")
				.replace("\"provinceName\"", "\"province\"").replace("\"city\"", "\"city_code\"")
				.replace("\"cityName\"", "\"city\"").replace("\"registrationTime\"", "\"register_date\"");
		return enterpriseInfoJson;
	}

	public static String enterpriseInfoBean(String jsonData) {
		String enterpriseInfoJson = jsonData.replace("\"taxpayer_identity_number\"", "\"taxpayerIdentityNumber\"")
				.replace("\"registered_capital\"", "\"registeredCapital\"").replace("\"province\"", "\"provinceName\"")
				.replace("\"province_code\"", "\"province\"").replace("\"city\"", "\"cityName\"")
				.replace("\"city_code\"", "\"city\"").replace("\"register_date\"", "\"registrationTime\"");
		return enterpriseInfoJson;
	}

	/**
	 * 财务负责人信息
	 *
	 * @param jsonData
	 * @return
	 */
	public static String financeChief(String jsonData) {
		String financeChiefJson = jsonData.replace("\"financeName\"", "\"name\"")
				.replace("\"financeIdCard\"", "\"identity\"").replace("\"fullPhone\"", "\"full_phone\"")
				.replace("\"subPhone\"", "\"sub_phone\"").replace("\"extNum\"", "\"ext_num\"");
		return financeChiefJson;
	}

	public static String financeChiefBean(String jsonData) {
		String financeChiefJson = jsonData.replace("\"name\"", "\"financeName\"")
				.replace("\"identity\"", "\"financeIdCard\"").replace("\"full_phone\"", "\"fullPhone\"")
				.replace("\"sub_phone\"", "\"subPhone\"").replace("\"ext_num\"", "\"extNum\"");
		return financeChiefJson;
	}

	/**
	 * 企业对公银行开户信息
	 *
	 * @param jsonData
	 * @return
	 */
	public static String enterpriseBankInfo(String jsonData) {
		String enterpriseBankInfoJson = jsonData.replace("\"bankCode\"", "\"bank_code\"")
				.replace("\"bankName\"", "\"bank_name\"").replace("\"bankSub\"", "\"bank_sub\"")
				.replace("\"bankProvince\"", "\"province\"").replace("\"bankCity\"", "\"city\"")
				.replace("\"provinceName\"", "\"province_name\"").replace("\"cityName\"", "\"city_name\"");
		return enterpriseBankInfoJson;
	}

	public static String enterpriseBankInfoBean(String jsonData) {
		String enterpriseBankInfoJson = jsonData.replace("\"bank_code\"", "\"bankCode\"")
				.replace("\"bank_name\"", "\"bankName\"").replace("\"bank_sub\"", "\"bankSub\"")
				.replace("\"province\"", "\"bankProvince\"").replace("\"city\"", "\"bankCity\"")
				.replace("\"province_name\"", "\"provinceName\"").replace("\"city_name\"", "\"cityName\"");
		return enterpriseBankInfoJson;
	}

	/**
	 * 企业办公电话
	 *
	 * @param enterpriseInfoData
	 * @return
	 */
/*	public static String enterpriseOfficeTelephone(String jsonData) {
		EnterpriseOfficeTelephone enterpriseOfficeTelephone = JSON.parseObject(
				JsonTransformUtil.enterpriseInfoBean(jsonData), new TypeReference<EnterpriseOfficeTelephone>() {
				});
		String enterpriseOfficeTelephoneJson = JSONObject.toJSONString(enterpriseOfficeTelephone)
				.replace("\"fullPhone\"", "\"full_phone\"").replace("\"subPhone\"", "\"sub_phone\"")
				.replace("\"extNum\"", "\"ext_num\"");
		return enterpriseOfficeTelephoneJson;
	}*/

	public static String enterpriseOfficeTelephoneBean(String jsonData) {
		String enterpriseOfficeTelephoneJson = jsonData.replace("\"full_phone\"", "\"fullPhone\"")
				.replace("\"sub_phone\"", "\"subPhone\"").replace("\"ext_num\"", "\"extNum\"");
		return enterpriseOfficeTelephoneJson;
	}

}
