package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoSignConfigReadService;
import com.egeo.components.order.service.write.SoSignConfigWriteService;


@Component
public class SoSignConfigFacade {
	
	@Resource
	private SoSignConfigReadService soSignConfigReadService;
	
	@Resource
	private SoSignConfigWriteService soSignConfigWriteService;
	


}
	