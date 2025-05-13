package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoInstallmentManage;
import com.egeo.components.order.facade.SoInstallmentFacade;

@Service("soInstallment")
public class SoInstallmentManageImpl implements SoInstallmentManage{

	
	@Resource(name="soInstallmentFacade")
	private SoInstallmentFacade soInstallmentFacade;
	


}
	