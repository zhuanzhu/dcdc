package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.DoItemReadService;
import com.egeo.components.order.service.write.DoItemWriteService;


@Component
public class DoItemFacade {
	
	@Resource
	private DoItemReadService doItemReadService;
	
	@Resource
	private DoItemWriteService doItemWriteService;
	


}
	