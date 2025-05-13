package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoExchangeManage;
import com.egeo.components.order.facade.SoExchangeFacade;

@Service("soExchange")
public class SoExchangeManageImpl implements SoExchangeManage{

	
	@Resource(name="soExchangeFacade")
	private SoExchangeFacade soExchangeFacade;
	


}
	