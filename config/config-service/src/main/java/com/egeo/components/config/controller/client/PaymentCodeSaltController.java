package com.egeo.components.config.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.client.PaymentCodeSaltClient;
import com.egeo.components.config.dto.PaymentCodeSaltDTO;
import com.egeo.components.config.service.read.PaymentCodeSaltReadService;
import com.egeo.components.config.service.write.PaymentCodeSaltWriteService;

@Controller
@RequestMapping("/client/config/paymentCodeSalt") 
public class PaymentCodeSaltController implements PaymentCodeSaltClient{ 

	@Autowired
	private PaymentCodeSaltReadService paymentCodeSaltReadService;
	@Autowired
	private PaymentCodeSaltWriteService paymentCodeSaltWriteService;


	@Override
	@RequestMapping(value = "/findSaltByUUID", method = { RequestMethod.POST })
	@ResponseBody
	public String findSaltByUUID(@RequestBody String uuid) {
		// TODO Auto-generated method stub
		return paymentCodeSaltReadService.findSaltByUUID(uuid);
	}

	@Override
	@RequestMapping(value = "/insertPaymentCodeSaltWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertPaymentCodeSaltWithTx(@RequestBody PaymentCodeSaltDTO paymentCodeSaltDTO) {
		// TODO Auto-generated method stub
		return paymentCodeSaltWriteService.insertPaymentCodeSaltWithTx(paymentCodeSaltDTO);
	}

 
}