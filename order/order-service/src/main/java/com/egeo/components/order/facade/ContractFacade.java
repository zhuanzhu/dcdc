package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.ContractReadService;
import com.egeo.components.order.service.write.ContractWriteService;


@Component
public class ContractFacade {
	
	@Resource
	private ContractReadService contractReadService;
	
	@Resource
	private ContractWriteService contractWriteService;
	


}
	