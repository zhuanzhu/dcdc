package com.egeo.components.order.service.write;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.InfoDTO;


public interface SendInfoWriteService {
	/**
	 * 发送消息
	 * @param dto
	 * @return
	 */
	public Boolean sendInfo(InfoDTO dto);
	/**
	 * 订单支付状态变更消息保存并发送
	 * @return
	 */
	public Long insertOrderPayStatusInfoAndSend(Long infoTemplateId,String orderCode,Integer orderPayStatus,Long userId);
	/**
	 * 订单确认状态变更消息保存并发送
	 *
     * @param platformId
     * @param infoTemplateId 消息模版id
     * @param orderCode
     * @param orderConfirmStatus 订单确认状态
     * @param userId
     * @return
	 */
	public Long insertOrderConfirmStatusInfoAndSend(Long platformId, Long infoTemplateId, String orderCode, Integer orderConfirmStatus, Long userId);
	
	/**
	 * 订单物流状态变更消息保存并发送
	 * @param infoTemplateId 消息模版id
	 * @param orderCode
	 * @param deliveryStatus 订单物流状态
	 * @param userId
	 * @return
	 */
	public Long insertOrderDeliveryStatusInfoAndSend(Long infoTemplateId,String orderCode,Integer deliveryStatus,Long userId);
	
	/**
	 * 用户积分变动消息保存并发送
	 * @param infoTemplateId
	 * @param changeFuBi
	 * @param changeCause
	 * @param userId
	 * @return
	 */
	public Long insertUserFuBiInfoAndSend(Long infoTemplateId,BigDecimal changeFuBi,String changeCause,Long userId);
	
	/**
	 * 用户点赞福豆变动消息保存并发送
	 * @param infoTemplateId
	 * @param changeFuBi
	 * @param changeCause
	 * @param userId
	 * @return
	 */
	public Long insertUserPraiseFuBiInfoAndSend(Long infoTemplateId,BigDecimal changeFuBi,String changeCause,Long userId);
	
	/**
	 * 企业积分变动消息保存并发送
	 * @param infoTemplateId
	 * @param changeFuBi
	 * @param changeCause
	 * @param userIds
	 * @return
	 */
	public Long insertCompanyFuBiInfoAndSend(Long infoTemplateId,BigDecimal changeFuBi,String changeCause,List<Long> userIds);
	
	/**
	 * 用户注册状态变更消息保存并发送
	 * @param infoTemplateId
	 * @param stateChangeCause 用户状态变更原因
	 * @param userId
	 * @return
	 */
	public Long insertUserStateChangeInfoAndSend(Long infoTemplateId,String stateChangeCause,Long userId);
	
	/**
	 * 用户离职消息保存并发送
	 * @param infoTemplateId
	 * @param DimissionTime 离职时间
	 * @param assetRecycle 资产回收时间
	 * @param userId
	 * @return
	 */
	public Long insertUserDimissionInfoAndSend(Long infoTemplateId,String DimissionTime,String assetRecycleTime, Long userId);
	
	/**
	 * 关联用户消息保存并发送
	 * @param infoTemplateId
	 * @param mail 邮箱地址
	 * @param companyName 公司名称
	 * @param userId
	 * @return
	 */
	public Long insertRelevanceUserInfoAndSend(Long infoTemplateId,String mobile,String bindingTime, String mail,String companyName, Long userId);
	
	/**
	 * 解绑用户消息保存并发送
	 * @param infoTemplateId
	 * @param mail 邮箱地址
	 * @param userId
	 * @return
	 */
	public Long insertUnbindUserInfoAndSend(Long infoTemplateId,String mobile,String unbindTime, String mail, Long userId);
	
	/**
	 * 平台发放优惠券消息保存并发送
	 * @param infoTemplateId
	 * @param issuingParty 发放方（平台/公司）
	 * @param couponName 券名称
	 * @param couponQuantity 券数量
	 * @param startTime 有效起始日期
	 * @param stopTime 有效截止日期
	 * @param userId 用户id
	 * @return
	 */
	public Long insertSendCouponInfoAndSend(Long infoTemplateId,String issuingParty,String couponName, Integer couponQuantity,String startTime,String stopTime, Long userId);
	
	/**
	 * 用户修改密码消息保存并发送
	 * @param infoTemplateId
	 * @param mail 邮件
	 * @param userId 用户id
	 * @param mobile
     * @return
	 */
	public Long insertUpdateUserPassWordInfoAndSend(Long infoTemplateId, String mail, Long userId, String mobile);
	
	/**
	 * 用户修改支付密码消息保存并发送
	 * @param infoTemplateId
	 * @param mail 邮件
	 * @param userId 用户id
	 * @return
	 */
	public Long insertUpdateUserPayPassWordInfoAndSend(Long infoTemplateId,String mail, Long userId);
	
	/**
	 * 用户报名活动消息保存并发送
	 * @param infoTemplateId
	 * @param activityName 活动名称
	 * @param startTime 活动开始时间
	 * @param userId
	 * @return
	 */
	public Long insertSignActivityInfoAndSend(Long infoTemplateId,String activityName,String startTime, Long userId);
	
	public Long insertAndSendMessage(Long infoTemplateId, Map<String, String> params, Long userId, String mobile);
	
}

	