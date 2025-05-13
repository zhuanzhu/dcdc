package com.egeo.components.order.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoInstallmentApprovalReadService;
import com.egeo.components.order.service.write.SoInstallmentApprovalWriteService;


@Component
public class SoInstallmentApprovalFacade {
	
	@Resource
	private SoInstallmentApprovalReadService soInstallmentApprovalReadService;
	
	@Resource
	private SoInstallmentApprovalWriteService soInstallmentApprovalWriteService;
	


}
	