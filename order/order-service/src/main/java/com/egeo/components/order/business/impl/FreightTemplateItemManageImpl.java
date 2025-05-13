package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.FreightTemplateItemManage;
import com.egeo.components.order.facade.FreightTemplateItemFacade;

@Service("freightTemplateItem")
public class FreightTemplateItemManageImpl implements FreightTemplateItemManage{

	
	@Resource(name="freightTemplateItemFacade")
	private FreightTemplateItemFacade freightTemplateItemFacade;
	


}
	