package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.MerchantFreightTemplateManage;
import com.egeo.components.order.facade.MerchantFreightTemplateFacade;

@Service("merchantFreightTemplate")
public class MerchantFreightTemplateManageImpl implements MerchantFreightTemplateManage{

	
	@Resource(name="merchantFreightTemplateFacade")
	private MerchantFreightTemplateFacade merchantFreightTemplateFacade;
	


}
	