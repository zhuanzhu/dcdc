package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoInstallmentApprovalManage;
import com.egeo.components.order.facade.SoInstallmentApprovalFacade;

@Service("soInstallmentApproval")
public class SoInstallmentApprovalManageImpl implements SoInstallmentApprovalManage{

	
	@Resource(name="soInstallmentApprovalFacade")
	private SoInstallmentApprovalFacade soInstallmentApprovalFacade;
	


}
	