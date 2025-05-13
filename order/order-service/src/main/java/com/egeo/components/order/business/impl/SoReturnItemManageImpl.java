package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoReturnItemManage;
import com.egeo.components.order.facade.SoReturnItemFacade;

@Service("soReturnItem")
public class SoReturnItemManageImpl implements SoReturnItemManage{

	
	@Resource(name="soReturnItemFacade")
	private SoReturnItemFacade soReturnItemFacade;
	


}
	