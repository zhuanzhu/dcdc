package com.egeo.components.user.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.dto.InfoTemplateDTO;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.keywordfilter.KeyWordMatchWithCache;

public class InfoTemplateReplaceKeyWordUtil {

	/**
	 * 订单支付状态消息处理
	 * @param infoTemplateDTO
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static InfoDTO orderPayStatusInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO,String orderCode,Integer orderPayStatus){
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(orderPayStatusInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(), orderCode, orderPayStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(orderPayStatusInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(), orderCode, orderPayStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(orderPayStatusInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(), orderCode, orderPayStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(orderPayStatusInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), orderCode, orderPayStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(orderPayStatusInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), orderCode, orderPayStatus));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	/**
	 * 订单支付状态消息替换关键词
	 * @param info
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static String orderPayStatusInfoReplaceKeyWord(String info,String orderCode,Integer orderPayStatus) {
		List<String> words = new ArrayList<String>();
		words.add("订单ID");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,orderCode);
		words.add("状态内容");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword,orderPayStatusToString(orderPayStatus));
	}
	
	/**
	 * 支付状态码转为字符
	 * @param orderPayStatus 订单支付状态 0:未支付、1:已支付、2:已退款
	 * @return
	 */
	private static String orderPayStatusToString(Integer orderPayStatus) {
		switch (orderPayStatus) {
		case 0:
			return "未支付";
		case 1:
			return "已支付";
		case 2:
			return "已退款";
		}
		return null;
	}
	
	/**
	 * 订单确认状态消息处理
	 * @param infoTemplateDTO
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static InfoDTO orderConfirmStatusInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO,String orderCode,Integer orderConfirmStatus){
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(orderConfirmStatusInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(), orderCode, orderConfirmStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(orderConfirmStatusInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(), orderCode, orderConfirmStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(orderConfirmStatusInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(), orderCode, orderConfirmStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(orderConfirmStatusInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), orderCode, orderConfirmStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(orderConfirmStatusInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), orderCode, orderConfirmStatus));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 订单确认状态消息替换关键词
	 * @param info
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static String orderConfirmStatusInfoReplaceKeyWord(String info,String orderCode,Integer orderConfirmStatus) {
		List<String> words = new ArrayList<String>();
		words.add("订单ID");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,orderCode);
		words.add("状态内容");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword,orderConfirmStatusToString(orderConfirmStatus));
	}
	/**
	 * 订单确认状态码转字符
	 * @param orderConfirmStatus
	 * @return
	 */
	private static String orderConfirmStatusToString(Integer orderConfirmStatus) {
		// 订单确认状态  0:未确认，1:已确认，2:已取消 3:已完成
		switch (orderConfirmStatus) {
		case 0:
			return "未确认";
		case 1:
			return "已确认";
		case 2:
			return "已取消";
		case 3:
			return "已完成";
		}
		return null;
	}
	
	/**
	 * 订单物流状态消息处理
	 * @param infoTemplateDTO
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static InfoDTO orderDeliveryStatusInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO,String orderCode,Integer deliveryStatus){
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(orderDeliveryStatusInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(), orderCode, deliveryStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(orderDeliveryStatusInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(), orderCode, deliveryStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(orderDeliveryStatusInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(), orderCode, deliveryStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(orderDeliveryStatusInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), orderCode, deliveryStatus));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(orderDeliveryStatusInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), orderCode, deliveryStatus));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 订单物流状态消息替换关键词
	 * @param info
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static String orderDeliveryStatusInfoReplaceKeyWord(String info,String orderCode,Integer deliveryStatus) {
		List<String> words = new ArrayList<String>();
		words.add("订单ID");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,orderCode);
		words.add("状态内容");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword,deliveryStatusToString(deliveryStatus));
	}
	
	/**
	 * 订单物流状态码转字符
	 * @return
	 */
	private static String deliveryStatusToString(Integer deliveryStatus) {
		// 订单发货状态，0：未发货 10：部分发货 20：已发货
		switch (deliveryStatus) {
		case 0:
			return "未发货";
		case 10:
			return "部分发货";
		case 20:
			return "已发货";
		}
		return null;
	}
	
	/**
	 * 用户积分变更消息处理
	 * @param infoTemplateDTO
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static InfoDTO userFubiChangeInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO,BigDecimal changeFuBi, String changeCause){
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(userFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(userFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(userFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(userFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(userFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), changeFuBi, changeCause));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 用户积分变更消息替换关键词
	 * @param info
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static String userFubiChangeInfoReplaceKeyWord(String info,BigDecimal changeFuBi, String changeCause) {
		if(EmptyUtil.isEmpty(changeFuBi)){
			changeFuBi = new BigDecimal(0L);
		}
		if(EmptyUtil.isEmpty(info)){
			info = "";
		}
		List<String> words = new ArrayList<String>();
		words.add("积分变动金额");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,changeFuBi.toString());
		words.add("变动原因内容");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword,changeCause);
	}
	
	/**
	 * 用户点赞福豆变更消息处理
	 * @param infoTemplateDTO
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static InfoDTO userPraiseFubiChangeInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO,BigDecimal changeFuBi, String changeCause){
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(userPraiseFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(userPraiseFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(userPraiseFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(userPraiseFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(userPraiseFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), changeFuBi, changeCause));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 用户点赞福豆变更消息替换关键词
	 * @param info
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static String userPraiseFubiChangeInfoReplaceKeyWord(String info,BigDecimal changeFuBi, String changeCause) {
		List<String> words = new ArrayList<String>();
		words.add("点赞变动金额");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,changeFuBi.toString());
		words.add("变动原因内容");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword,changeCause);
	}
	
	/**
	 * 公司积分变更消息处理
	 * @param infoTemplateDTO
	 * @param changeFuBi 变更积分金额
	 * @param changeCause 变更原因
	 * @return
	 */
	public static InfoDTO companyFubiChangeInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO,BigDecimal changeFuBi, String changeCause){
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(companyFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(companyFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(companyFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(companyFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), changeFuBi, changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(companyFubiChangeInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), changeFuBi, changeCause));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 公司积分变更消息替换关键词
	 * @param info
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static String companyFubiChangeInfoReplaceKeyWord(String info,BigDecimal changeFuBi, String changeCause) {
		List<String> words = new ArrayList<String>();
		words.add("余额变动金额");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,changeFuBi.toString());
		words.add("变动原因内容");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword,changeCause);
	}
	
	/**
	 * 用户注册状态变更消息处理
	 * @param infoTemplateDTO
	 * @param changeCause 变更原因
	 * @return
	 */
	public static InfoDTO userStateChangeInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO, String changeCause){
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(userStateChangeInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(), changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(userStateChangeInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(), changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(userStateChangeInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(), changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(userStateChangeInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), changeCause));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(userStateChangeInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), changeCause));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 用户注册状态变更消息替换关键词
	 * @param info
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static String userStateChangeInfoReplaceKeyWord(String info, String changeCause) {
		List<String> words = new ArrayList<String>();
		words.add("状态内容");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(info,changeCause);
	}
	
	/**
	 * 用户离职状态变更消息处理
	 * @param infoTemplateDTO
	 * @param DimissionTime 离职时间
	 * @param assetRecycleTime 资产回收时间
	 * @return
	 */
	public static InfoDTO userDimissionInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO, String DimissionTime, String assetRecycleTime){
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(userDimissionInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(), DimissionTime,assetRecycleTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(userDimissionInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(), DimissionTime,assetRecycleTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(userDimissionInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(), DimissionTime,assetRecycleTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(userDimissionInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), DimissionTime,assetRecycleTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(userDimissionInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(), DimissionTime,assetRecycleTime));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 用户离职状态变更消息替换关键词
	 * @param info
	 * @return
	 */
	public static String userDimissionInfoReplaceKeyWord(String info,String DimissionTime, String assetRecycleTime) {
		List<String> words = new ArrayList<String>();
		words.add("离职日期");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,DimissionTime);
		words.add("失效日期");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword,assetRecycleTime);
	}
	
	/**
	 * 关联用户消息处理
	 * @param infoTemplateDTO
	 * @param DimissionTime 
	 * @param assetRecycleTime
	 * @return
	 */
	public static InfoDTO relevanceUserInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO, String mobile,String bindingTime, String mail,String companyName){
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(relevanceUserInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(),mobile,bindingTime, mail,companyName));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(relevanceUserInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(),mobile,bindingTime, mail,companyName));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(relevanceUserInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(),mobile,bindingTime, mail,companyName));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(relevanceUserInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),mobile,bindingTime, mail,companyName));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(relevanceUserInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),mobile,bindingTime, mail,companyName));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 关联用户消息替换关键词
	 * @param info
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static String relevanceUserInfoReplaceKeyWord(String info,String mobile,String bindingTime, String mail,String companyName) {
		List<String> words = new ArrayList<String>();
		words.add("新手机号");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,mobile);
		words.add("绑定时间");
		cache = new KeyWordMatchWithCache(words);
		String allKeyword2 = cache.replaceAllKeywordAppointString(allKeyword,bindingTime);
		words.add("邮箱地址");
		cache = new KeyWordMatchWithCache(words);
		String allKeyword3 = cache.replaceAllKeywordAppointString(allKeyword2,mail);
		words.add("公司名称");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword3,companyName);
	}
	
	/**
	 * 关联用户消息处理
	 * @param infoTemplateDTO
	 * @param DimissionTime 
	 * @param assetRecycleTime
	 * @return
	 */
	public static InfoDTO unbindUserInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO, String mobile,String unbindTime, String mail){
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(unbindUserInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(),mobile,unbindTime, mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(unbindUserInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(),mobile,unbindTime, mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(unbindUserInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(),mobile,unbindTime, mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(unbindUserInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),mobile,unbindTime, mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(unbindUserInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),mobile,unbindTime, mail));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 关联用户消息替换关键词
	 * @param info
	 * @param orderCode
	 * @param orderPayStatus
	 * @return
	 */
	public static String unbindUserInfoReplaceKeyWord(String info,String mobile,String unbindTime, String mail) {
		List<String> words = new ArrayList<String>();
		words.add("旧手机号码");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,mobile);
		words.add("解绑时间");
		cache = new KeyWordMatchWithCache(words);
		String allKeyword2 = cache.replaceAllKeywordAppointString(allKeyword,unbindTime);
		words.add("解绑账号");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword2,mail);
	}
	/**
	 * 平台发放优惠券消息替换关键词
	 * @param infoTemplateDTO
	 * @param issuingParty 发放方（平台/公司）
	 * @param couponName 券名称
	 * @param couponQuantity 券数量
	 * @param startTime 有效起始日期
	 * @param stopTime 有效截止日期
	 * @return
	 */
	public static InfoDTO sendCouponInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO, String issuingParty,
			String couponName, Integer couponQuantity, String startTime, String stopTime) {
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(sendCouponInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(),issuingParty,couponName, couponQuantity,startTime,stopTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(sendCouponInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(),issuingParty,couponName, couponQuantity,startTime,stopTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(sendCouponInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(),issuingParty,couponName, couponQuantity,startTime,stopTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(sendCouponInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),issuingParty,couponName, couponQuantity,startTime,stopTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(sendCouponInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),issuingParty,couponName, couponQuantity,startTime,stopTime));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 平台发放优惠券消息替换关键词
	 * 发放方（平台/公司）、券名称、券数量、有效起始日期、有效截止日期
	 * @return
	 */
	public static String sendCouponInfoReplaceKeyWord(String info,String issuingParty,
			String couponName, Integer couponQuantity, String startTime, String stopTime) {
		List<String> words = new ArrayList<String>();
		words.add("发放方（公司名称）");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,issuingParty);
		words.add("券名称");
		cache = new KeyWordMatchWithCache(words);
		String allKeyword2 = cache.replaceAllKeywordAppointString(allKeyword,couponName);
		words.add("券数量");
		cache = new KeyWordMatchWithCache(words);
		String allKeyword3 = cache.replaceAllKeywordAppointString(allKeyword2,couponQuantity.toString());
		words.add("有效起始日期");
		cache = new KeyWordMatchWithCache(words);
		String allKeyword4 = cache.replaceAllKeywordAppointString(allKeyword3,startTime);
		words.add("有效截止日期");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword4,stopTime);
	}
	/**
	 * 修改用户密码消息替换关键词
	 * @param infoTemplateDTO2
	 * @param mail 邮件地址
	 * @return
	 */
	public static InfoDTO updateUserPassWordInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO, String mail) {
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(updateUserPassWordInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(),mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(updateUserPassWordInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(),mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(updateUserPassWordInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(),mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(updateUserPassWordInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(updateUserPassWordInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),mail));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	/**
	 * 修改用户密码消息替换关键词
	 * @param info
	 * @param mail
	 * @return
	 */
	private static String updateUserPassWordInfoReplaceKeyWord(String info, String mail) {
		List<String> words = new ArrayList<String>();
		words.add("邮箱地址");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(info,mail);
	}
	/**
	 * 修改用户支付密码消息替换关键词
	 * @param info
	 * @param mail
	 * @return
	 */
	public static InfoDTO updateUserPayPassWordInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO,
			String mail) {
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(updateUserPassWordInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(),mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(updateUserPassWordInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(),mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(updateUserPassWordInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(),mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(updateUserPassWordInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),mail));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(updateUserPassWordInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),mail));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	/**
	 * 用户报名活动消息保存并发送
	 * @param infoTemplateDTO2
	 * @param activityName 活动名称
	 * @param startTime 开始时间
	 * @return
	 */
	public static InfoDTO signActivityInfoTemplateReplaceKeyWord(InfoTemplateDTO infoTemplateDTO, String activityName,
			String startTime) {
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(signActivityInfoReplaceKeyWord(infoTemplateDTO.getSystemInfo(),activityName,startTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(signActivityInfoReplaceKeyWord(infoTemplateDTO.getInfoInform(),activityName,startTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(signActivityInfoReplaceKeyWord(infoTemplateDTO.getMoblieInfo(),activityName,startTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(signActivityInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),activityName,startTime));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo()))
			infoDTO.setMailInfo(signActivityInfoReplaceKeyWord(infoTemplateDTO.getMailInfo(),activityName,startTime));
		infoDTO.setMailInfoTitle(infoTemplateDTO.getMailInfoTitle());
		return infoDTO;
	}
	
	/**
	 * 用户报名活动消息替换关键词
	 * @param info
	 * @return
	 */
	public static String signActivityInfoReplaceKeyWord(String info,String activityName, String startTime) {
		List<String> words = new ArrayList<String>();
		words.add("活动名称");
		KeyWordMatchWithCache cache = new KeyWordMatchWithCache(words);
		String allKeyword = cache.replaceAllKeywordAppointString(info,activityName);
		words.add("活动开始时间");
		cache = new KeyWordMatchWithCache(words);
		return cache.replaceAllKeywordAppointString(allKeyword,startTime);
	}
}
