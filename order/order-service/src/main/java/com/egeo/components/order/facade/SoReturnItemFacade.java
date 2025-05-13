package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoReturnItemReadService;
import com.egeo.components.order.service.write.SoReturnItemWriteService;


@Component
public class SoReturnItemFacade {
	
	@Resource
	private SoReturnItemReadService soReturnItemReadService;
	
	@Resource
	private SoReturnItemWriteService soReturnItemWriteService;
	


}
	