package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.MerchantExpressConfigReadService;
import com.egeo.components.order.service.write.MerchantExpressConfigWriteService;


@Component
public class MerchantExpressConfigFacade {
	
	@Resource
	private MerchantExpressConfigReadService merchantExpressConfigReadService;
	
	@Resource
	private MerchantExpressConfigWriteService merchantExpressConfigWriteService;
	


}
	