package com.egeo.components.promotion.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.ExchangeActivityClient;
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.components.promotion.service.read.ExchangeActivityReadService;
import com.egeo.components.promotion.service.write.ExchangeActivityWriteService;

@Controller
@RequestMapping("/client/promotion/exchangeActivity") 
public class ExchangeActivityController implements ExchangeActivityClient{ 

	@Autowired
	private ExchangeActivityReadService exchangeActivityReadService;
	@Autowired
	private ExchangeActivityWriteService exchangeActivityWriteService;


	@Override
	@RequestMapping(value = "/findExchangeActivityById", method = { RequestMethod.POST })
	@ResponseBody
	public ExchangeActivityDTO findExchangeActivityById(@RequestBody ExchangeActivityDTO dto) {
		return exchangeActivityReadService.findExchangeActivityById(dto);
	} 
} 
