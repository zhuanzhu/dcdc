package com.egeo.components.user.client;
import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.vo.InsertOrderPayStatusInfoAndSendVO;


@FeignClient(name = "service-user-fgj",contextId="SendInfoClient")
public interface SendInfoClient {

	
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
	@RequestMapping(value = { "/client/user/sendInfo/insertOrderConfirmStatusInfoAndSend" }, method = { RequestMethod.POST }) 
	public Long insertOrderConfirmStatusInfoAndSend(InsertAndSendMessageDTO dto);
	
	/**
	 * 订单物流状态变更消息保存并发送
	 * @param infoTemplateId 消息模版id
	 * @param orderCode
	 * @param deliveryStatus 订单物流状态
	 * @param userId
	 * @return
	 */

	@RequestMapping(value = { "/client/user/sendInfo/insertOrderDeliveryStatusInfoAndSend" }, method = { RequestMethod.POST }) 
	public Long insertOrderDeliveryStatusInfoAndSend(InsertAndSendMessageDTO dto);

	@RequestMapping(value = { "/client/user/sendInfo/insertSendCouponInfoAndSend" }, method = { RequestMethod.POST }) 
	public Long insertSendCouponInfoAndSend(InsertAndSendMessageDTO dto); 
 
	/**
	 * 订单支付状态变更消息保存并发送
	 * @return
	 */
	@RequestMapping(value = { "/client/user/sendInfo/insertOrderPayStatusInfoAndSend" }, method = { RequestMethod.POST }) 
	//public Long insertOrderPayStatusInfoAndSend( Long infoTemplateId, String orderCode,  Integer orderPayStatus,  Long userId);
public Long insertOrderPayStatusInfoAndSend(InsertOrderPayStatusInfoAndSendVO vo);
	/**
	 * 用户积分变动消息保存并发送
	 * @param infoTemplateId
	 * @param changeFuBi
	 * @param changeCause
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = { "/client/user/sendInfo/insertUserFuBiInfoAndSend" }, method = { RequestMethod.POST }) 
	public Long insertUserFuBiInfoAndSend(InsertAndSendMessageDTO dto);

	@RequestMapping(value = { "/client/user/sendInfo/insertAndSendMessage" }, method = { RequestMethod.POST }) 
	public Long insertAndSendMessage(InsertAndSendMessageDTO dto);
	
	@RequestMapping(value = { "/client/user/sendInfo/insertCompanyFuBiInfoAndSend" }, method = { RequestMethod.POST }) 
	public Long insertCompanyFuBiInfoAndSend(InsertAndSendMessageDTO dto) ;
	
}

