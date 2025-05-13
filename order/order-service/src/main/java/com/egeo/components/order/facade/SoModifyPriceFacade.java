package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoModifyPriceReadService;
import com.egeo.components.order.service.write.SoModifyPriceWriteService;


@Component
public class SoModifyPriceFacade {
	
	@Resource
	private SoModifyPriceReadService soModifyPriceReadService;
	
	@Resource
	private SoModifyPriceWriteService soModifyPriceWriteService;
	


}
	