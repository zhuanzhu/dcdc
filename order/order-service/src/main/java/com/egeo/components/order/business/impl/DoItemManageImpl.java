package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.DoItemManage;
import com.egeo.components.order.facade.DoItemFacade;

@Service("doItem")
public class DoItemManageImpl implements DoItemManage{

	
	@Resource(name="doItemFacade")
	private DoItemFacade doItemFacade;
	


}
	