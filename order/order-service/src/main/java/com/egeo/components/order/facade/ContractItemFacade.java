package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.ContractItemReadService;
import com.egeo.components.order.service.write.ContractItemWriteService;


@Component
public class ContractItemFacade {
	
	@Resource
	private ContractItemReadService contractItemReadService;
	
	@Resource
	private ContractItemWriteService contractItemWriteService;
	


}
	