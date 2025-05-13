package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.CustomerConditionReadService;
import com.egeo.components.order.service.write.CustomerConditionWriteService;


@Component
public class CustomerConditionFacade {
	
	@Resource
	private CustomerConditionReadService customerConditionReadService;
	
	@Resource
	private CustomerConditionWriteService customerConditionWriteService;
	


}
	