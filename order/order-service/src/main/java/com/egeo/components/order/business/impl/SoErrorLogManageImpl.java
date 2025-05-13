package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoErrorLogManage;
import com.egeo.components.order.facade.SoErrorLogFacade;

@Service("soErrorLog")
public class SoErrorLogManageImpl implements SoErrorLogManage{

	
	@Resource(name="soErrorLogFacade")
	private SoErrorLogFacade soErrorLogFacade;
	


}
	