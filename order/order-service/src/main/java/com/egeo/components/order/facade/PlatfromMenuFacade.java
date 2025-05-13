package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.PlatfromMenuReadService;
import com.egeo.components.order.service.write.PlatfromMenuWriteService;


@Component
public class PlatfromMenuFacade {
	
	@Resource
	private PlatfromMenuReadService platfromMenuReadService;
	
	@Resource
	private PlatfromMenuWriteService platfromMenuWriteService;
	


}
	