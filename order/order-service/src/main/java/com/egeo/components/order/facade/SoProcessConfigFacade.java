package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoProcessConfigReadService;
import com.egeo.components.order.service.write.SoProcessConfigWriteService;


@Component
public class SoProcessConfigFacade {
	
	@Resource
	private SoProcessConfigReadService soProcessConfigReadService;
	
	@Resource
	private SoProcessConfigWriteService soProcessConfigWriteService;
	


}
	