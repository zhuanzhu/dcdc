package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.FreightTemplateReadService;
import com.egeo.components.order.service.write.FreightTemplateWriteService;


@Component
public class FreightTemplateFacade {
	
	@Resource
	private FreightTemplateReadService freightTemplateReadService;
	
	@Resource
	private FreightTemplateWriteService freightTemplateWriteService;
	


}
	