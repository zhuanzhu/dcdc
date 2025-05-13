package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.ExpressConfigManage;
import com.egeo.components.order.facade.ExpressConfigFacade;

@Service("expressConfig")
public class ExpressConfigManageImpl implements ExpressConfigManage{

	
	@Resource(name="expressConfigFacade")
	private ExpressConfigFacade expressConfigFacade;
	


}
	