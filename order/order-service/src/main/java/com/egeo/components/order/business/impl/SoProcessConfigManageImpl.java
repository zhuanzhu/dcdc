package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoProcessConfigManage;
import com.egeo.components.order.facade.SoProcessConfigFacade;

@Service("soProcessConfig")
public class SoProcessConfigManageImpl implements SoProcessConfigManage{

	
	@Resource(name="soProcessConfigFacade")
	private SoProcessConfigFacade soProcessConfigFacade;
	


}
	