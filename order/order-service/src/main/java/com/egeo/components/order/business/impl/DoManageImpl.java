package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.DoManage;
import com.egeo.components.order.facade.DoFacade;

@Service("do")
public class DoManageImpl implements DoManage{

	
	@Resource(name="doFacade")
	private DoFacade doFacade;
	


}
	