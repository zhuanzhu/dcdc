package com.egeo.components.config.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.dto.PaymentCodeSaltDTO;

@FeignClient(name = "service-config-fgj",contextId="PaymentCodeSaltClient")
public interface PaymentCodeSaltClient {

	@RequestMapping(value = { "/client/config/paymentCodeSalt/findSaltByUUID" }, method = { RequestMethod.POST })
	public String findSaltByUUID(String uuid);

	@RequestMapping(value = { "/client/config/paymentCodeSalt/insertPaymentCodeSaltWithTx" }, method = { RequestMethod.POST })
	public Long insertPaymentCodeSaltWithTx(PaymentCodeSaltDTO paymentCodeSaltDTO);
	
}
