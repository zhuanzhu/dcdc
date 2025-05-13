package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.MerchantFreightTemplateReadService;
import com.egeo.components.order.service.write.MerchantFreightTemplateWriteService;


@Component
public class MerchantFreightTemplateFacade {
	
	@Resource
	private MerchantFreightTemplateReadService merchantFreightTemplateReadService;
	
	@Resource
	private MerchantFreightTemplateWriteService merchantFreightTemplateWriteService;
	


}
	