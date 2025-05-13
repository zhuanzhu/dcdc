package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoExchangeReadService;
import com.egeo.components.order.service.write.SoExchangeWriteService;


@Component
public class SoExchangeFacade {
	
	@Resource
	private SoExchangeReadService soExchangeReadService;
	
	@Resource
	private SoExchangeWriteService soExchangeWriteService;
	


}
	