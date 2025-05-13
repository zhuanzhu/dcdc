package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoReturnConfigManage;
import com.egeo.components.order.facade.SoReturnConfigFacade;

@Service("soReturnConfig")
public class SoReturnConfigManageImpl implements SoReturnConfigManage{

	
	@Resource(name="soReturnConfigFacade")
	private SoReturnConfigFacade soReturnConfigFacade;
	


}
	