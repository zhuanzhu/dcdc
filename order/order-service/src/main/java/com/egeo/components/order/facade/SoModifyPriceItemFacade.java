package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoModifyPriceItemReadService;
import com.egeo.components.order.service.write.SoModifyPriceItemWriteService;


@Component
public class SoModifyPriceItemFacade {
	
	@Resource
	private SoModifyPriceItemReadService soModifyPriceItemReadService;
	
	@Resource
	private SoModifyPriceItemWriteService soModifyPriceItemWriteService;
	


}
	