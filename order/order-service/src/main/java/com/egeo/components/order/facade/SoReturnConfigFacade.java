package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoReturnConfigReadService;
import com.egeo.components.order.service.write.SoReturnConfigWriteService;


@Component
public class SoReturnConfigFacade {
	
	@Resource
	private SoReturnConfigReadService soReturnConfigReadService;
	
	@Resource
	private SoReturnConfigWriteService soReturnConfigWriteService;
	


}
	