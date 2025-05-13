package com.egeo.components.pay.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.pay.client.PayWeixinLogClient;
import com.egeo.components.pay.dto.PayWeixinLogDTO;
import com.egeo.components.pay.service.read.PayWeixinLogReadService;
import com.egeo.components.pay.service.write.PayWeixinLogWriteService;

@Controller
@RequestMapping("/client/pay/payWeixinLog") 
public class PayWeixinLogController implements PayWeixinLogClient{ 

	@Autowired
	private PayWeixinLogReadService payWeixinLogReadService;
	@Autowired
	private PayWeixinLogWriteService payWeixinLogWriteService;



	@Override
	@RequestMapping(value = "/queryPayWeixinLogByOrderCode", method = { RequestMethod.POST })
	@ResponseBody
	public PayWeixinLogDTO queryPayWeixinLogByOrderCode(@RequestBody String orderCode) {
		// TODO Auto-generated method stub
		return payWeixinLogReadService.queryPayWeixinLogByOrderCode(orderCode);
	} 
 
 
}