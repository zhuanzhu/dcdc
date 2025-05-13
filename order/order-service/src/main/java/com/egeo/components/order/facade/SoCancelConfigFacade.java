package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoCancelConfigReadService;
import com.egeo.components.order.service.write.SoCancelConfigWriteService;


@Component
public class SoCancelConfigFacade {
	
	@Resource
	private SoCancelConfigReadService soCancelConfigReadService;
	
	@Resource
	private SoCancelConfigWriteService soCancelConfigWriteService;
	


}
	