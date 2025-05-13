package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.DoReadService;
import com.egeo.components.order.service.write.DoWriteService;


@Component
public class DoFacade {
	
	@Resource
	private DoReadService doReadService;
	
	@Resource
	private DoWriteService doWriteService;
	


}
	