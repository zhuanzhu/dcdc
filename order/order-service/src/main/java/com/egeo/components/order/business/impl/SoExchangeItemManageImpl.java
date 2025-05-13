package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoExchangeItemManage;
import com.egeo.components.order.facade.SoExchangeItemFacade;

@Service("soExchangeItem")
public class SoExchangeItemManageImpl implements SoExchangeItemManage{

	
	@Resource(name="soExchangeItemFacade")
	private SoExchangeItemFacade soExchangeItemFacade;
	


}
	