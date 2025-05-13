package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoExchangeItemReadService;
import com.egeo.components.order.service.write.SoExchangeItemWriteService;


@Component
public class SoExchangeItemFacade {
	
	@Resource
	private SoExchangeItemReadService soExchangeItemReadService;
	
	@Resource
	private SoExchangeItemWriteService soExchangeItemWriteService;
	


}
	