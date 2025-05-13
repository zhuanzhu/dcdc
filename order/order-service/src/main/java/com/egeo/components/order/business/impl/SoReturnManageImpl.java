package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoReturnManage;
import com.egeo.components.order.facade.SoReturnFacade;

@Service("soReturn")
public class SoReturnManageImpl implements SoReturnManage{

	
	@Resource(name="soReturnFacade")
	private SoReturnFacade soReturnFacade;
	


}
	