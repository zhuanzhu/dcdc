package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.FreightTemplateItemReadService;
import com.egeo.components.order.service.write.FreightTemplateItemWriteService;


@Component
public class FreightTemplateItemFacade {
	
	@Resource
	private FreightTemplateItemReadService freightTemplateItemReadService;
	
	@Resource
	private FreightTemplateItemWriteService freightTemplateItemWriteService;
	


}
	