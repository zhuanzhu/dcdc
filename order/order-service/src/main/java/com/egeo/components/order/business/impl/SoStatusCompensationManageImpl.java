package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoStatusCompensationManage;
import com.egeo.components.order.facade.SoStatusCompensationFacade;

@Service("soStatusCompensation")
public class SoStatusCompensationManageImpl implements SoStatusCompensationManage{

	
	@Resource(name="soStatusCompensationFacade")
	private SoStatusCompensationFacade soStatusCompensationFacade;
	


}
	