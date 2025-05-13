package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoMessageRelManage;
import com.egeo.components.order.facade.SoMessageRelFacade;

@Service("soMessageRel")
public class SoMessageRelManageImpl implements SoMessageRelManage{

	
	@Resource(name="soMessageRelFacade")
	private SoMessageRelFacade soMessageRelFacade;
	


}
	