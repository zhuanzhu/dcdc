package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoErrorLogReadService;
import com.egeo.components.order.service.write.SoErrorLogWriteService;


@Component
public class SoErrorLogFacade {
	
	@Resource
	private SoErrorLogReadService soErrorLogReadService;
	
	@Resource
	private SoErrorLogWriteService soErrorLogWriteService;
	


}
	