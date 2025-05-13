package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.ConditionPoolReadService;
import com.egeo.components.order.service.write.ConditionPoolWriteService;


@Component
public class ConditionPoolFacade {
	
	@Resource
	private ConditionPoolReadService conditionPoolReadService;
	
	@Resource
	private ConditionPoolWriteService conditionPoolWriteService;
	


}
	