package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.ExpressMapConfigReadService;
import com.egeo.components.order.service.write.ExpressMapConfigWriteService;


@Component
public class ExpressMapConfigFacade {
	
	@Resource
	private ExpressMapConfigReadService expressMapConfigReadService;
	
	@Resource
	private ExpressMapConfigWriteService expressMapConfigWriteService;
	


}
	