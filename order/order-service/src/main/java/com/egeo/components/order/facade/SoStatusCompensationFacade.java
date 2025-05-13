package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoStatusCompensationReadService;
import com.egeo.components.order.service.write.SoStatusCompensationWriteService;


@Component
public class SoStatusCompensationFacade {
	
	@Resource
	private SoStatusCompensationReadService soStatusCompensationReadService;
	
	@Resource
	private SoStatusCompensationWriteService soStatusCompensationWriteService;
	


}
	