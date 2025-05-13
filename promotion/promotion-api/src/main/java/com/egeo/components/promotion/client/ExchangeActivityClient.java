package com.egeo.components.promotion.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.promotion.dto.ExchangeActivityDTO;


@FeignClient(name = "service-promotion-fgj",contextId="ExchangeActivityClient")
public interface ExchangeActivityClient {

	@RequestMapping(value = { "/client/promotion/exchangeActivity/findExchangeActivityById" }, method = { RequestMethod.POST }) 
	public ExchangeActivityDTO findExchangeActivityById(ExchangeActivityDTO dto); 
 
 
}