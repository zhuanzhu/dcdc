package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoLeaseReadService;
import com.egeo.components.order.service.write.SoLeaseWriteService;


@Component
public class SoLeaseFacade {
	
	@Resource
	private SoLeaseReadService soLeaseReadService;
	
	@Resource
	private SoLeaseWriteService soLeaseWriteService;
	


}
	