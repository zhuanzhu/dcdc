package com.egeo.components.user.controller.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.service.write.SendInfoWriteService;
import com.egeo.components.user.vo.InsertOrderPayStatusInfoAndSendVO;

@Controller
@RequestMapping("/client/user/sendInfo") 
public class SendInfoController implements SendInfoClient{ 

	@Autowired
	private SendInfoWriteService sendInfoWriteService;
	@Override
	@RequestMapping(value = { "/insertSendCouponInfoAndSend" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public Long insertSendCouponInfoAndSend(@RequestBody InsertAndSendMessageDTO dto){
		return sendInfoWriteService.insertSendCouponInfoAndSend(dto.getInfoTemplateId(), dto.getParams().get("issuingParty"), dto.getParams().get("couponName"),  Integer.valueOf(dto.getParams().get("couponQuantity")), dto.getParams().get("startTime"), dto.getParams().get("stopTime"), dto.getUserId());
	}

	@Override
	@RequestMapping(value = { "/insertOrderPayStatusInfoAndSend" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public Long insertOrderPayStatusInfoAndSend(@RequestBody InsertOrderPayStatusInfoAndSendVO vo) {
		Long infoTemplateId= vo.getInfoTemplateId();
		String orderCode=vo.getOrderCode();
		Integer orderPayStatus=vo.getOrderPayStatus();
		Long userId =vo.getUserId();
		// TODO Auto-generated method stub
		return sendInfoWriteService.insertOrderPayStatusInfoAndSend(infoTemplateId, orderCode, orderPayStatus, userId);
	}

	@Override
	@RequestMapping(value = { "/insertUserFuBiInfoAndSend" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public Long insertUserFuBiInfoAndSend(@RequestBody InsertAndSendMessageDTO dto) {
		// TODO Auto-generated method stub
		return sendInfoWriteService.insertUserFuBiInfoAndSend(dto.getInfoTemplateId(), new BigDecimal(dto.getParams().get("changeFuBi")), dto.getParams().get("changeCause"), dto.getUserId());
	}

	@Override
	@RequestMapping(value = { "/insertCompanyFuBiInfoAndSend" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public Long insertCompanyFuBiInfoAndSend(@RequestBody InsertAndSendMessageDTO dto) {
		// TODO Auto-generated method stub
		String[] ids = dto.getParams().get("userIds").split(",");
		List<Long> userIds = new ArrayList<Long>();
		for(String one :ids ) {
			userIds.add(Long.valueOf(one));
		}
		return sendInfoWriteService.insertCompanyFuBiInfoAndSend(dto.getInfoTemplateId(), new BigDecimal(dto.getParams().get("changeFuBi")), dto.getParams().get("changeCause"), userIds);
	}
	
	
	@Override
	@RequestMapping(value = { "/insertAndSendMessage" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public Long insertAndSendMessage(@RequestBody InsertAndSendMessageDTO dto) {
		// TODO Auto-generated method stub
		return sendInfoWriteService.insertAndSendMessage(dto.getInfoTemplateId(), dto.getParams(), dto.getUserId(), dto.getMobile());
	}

	@Override
	@RequestMapping(value = { "/insertOrderConfirmStatusInfoAndSend" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public Long insertOrderConfirmStatusInfoAndSend(@RequestBody InsertAndSendMessageDTO dto) {
		// TODO Auto-generated method stub
		return sendInfoWriteService.insertOrderConfirmStatusInfoAndSend(Long.valueOf(dto.getParams().get("platformId")), dto.getInfoTemplateId(), dto.getParams().get("orderCode"), Integer.valueOf(dto.getParams().get("orderConfirmStatus")), dto.getUserId());
	}

	@Override
	@RequestMapping(value = { "/insertOrderDeliveryStatusInfoAndSend" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public Long insertOrderDeliveryStatusInfoAndSend(@RequestBody InsertAndSendMessageDTO dto) {
		// TODO Auto-generated method stub
		return sendInfoWriteService.insertOrderDeliveryStatusInfoAndSend(dto.getInfoTemplateId(), dto.getParams().get("orderCode"), Integer.valueOf(dto.getParams().get("deliveryStatus")), dto.getUserId());
	} 
 
}


