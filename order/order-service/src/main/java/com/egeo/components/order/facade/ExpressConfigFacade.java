package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.ExpressConfigReadService;
import com.egeo.components.order.service.write.ExpressConfigWriteService;


@Component
public class ExpressConfigFacade {
	
	@Resource
	private ExpressConfigReadService expressConfigReadService;
	
	@Resource
	private ExpressConfigWriteService expressConfigWriteService;
	


}
	