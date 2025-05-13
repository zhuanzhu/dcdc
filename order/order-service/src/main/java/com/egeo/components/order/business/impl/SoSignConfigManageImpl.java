package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoSignConfigManage;
import com.egeo.components.order.facade.SoSignConfigFacade;

@Service("soSignConfig")
public class SoSignConfigManageImpl implements SoSignConfigManage{

	
	@Resource(name="soSignConfigFacade")
	private SoSignConfigFacade soSignConfigFacade;
	


}
	