package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoReturnReadService;
import com.egeo.components.order.service.write.SoReturnWriteService;


@Component
public class SoReturnFacade {
	
	@Resource
	private SoReturnReadService soReturnReadService;
	
	@Resource
	private SoReturnWriteService soReturnWriteService;
	


}
	