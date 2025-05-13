package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoMessageRelReadService;
import com.egeo.components.order.service.write.SoMessageRelWriteService;


@Component
public class SoMessageRelFacade {
	
	@Resource
	private SoMessageRelReadService soMessageRelReadService;
	
	@Resource
	private SoMessageRelWriteService soMessageRelWriteService;
	


}
	