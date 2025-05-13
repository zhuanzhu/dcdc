package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoCancelConfigManage;
import com.egeo.components.order.facade.SoCancelConfigFacade;

@Service("soCancelConfig")
public class SoCancelConfigManageImpl implements SoCancelConfigManage{

	
	@Resource(name="soCancelConfigFacade")
	private SoCancelConfigFacade soCancelConfigFacade;
	


}
	