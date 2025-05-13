package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.PlatfromConditionReadService;
import com.egeo.components.order.service.write.PlatfromConditionWriteService;


@Component
public class PlatfromConditionFacade {
	
	@Resource
	private PlatfromConditionReadService platfromConditionReadService;
	
	@Resource
	private PlatfromConditionWriteService platfromConditionWriteService;
	


}
	