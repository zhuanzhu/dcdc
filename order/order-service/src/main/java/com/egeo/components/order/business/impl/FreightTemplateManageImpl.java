package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.FreightTemplateManage;
import com.egeo.components.order.facade.FreightTemplateFacade;

@Service("freightTemplate")
public class FreightTemplateManageImpl implements FreightTemplateManage{

	
	@Resource(name="freightTemplateFacade")
	private FreightTemplateFacade freightTemplateFacade;
	


}
	