package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoInstallmentReadService;
import com.egeo.components.order.service.write.SoInstallmentWriteService;


@Component
public class SoInstallmentFacade {
	
	@Resource
	private SoInstallmentReadService soInstallmentReadService;
	
	@Resource
	private SoInstallmentWriteService soInstallmentWriteService;
	


}
	