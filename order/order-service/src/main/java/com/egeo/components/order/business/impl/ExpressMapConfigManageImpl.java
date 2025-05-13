package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.ExpressMapConfigManage;
import com.egeo.components.order.facade.ExpressMapConfigFacade;

@Service("expressMapConfig")
public class ExpressMapConfigManageImpl implements ExpressMapConfigManage{

	
	@Resource(name="expressMapConfigFacade")
	private ExpressMapConfigFacade expressMapConfigFacade;
	


}
	