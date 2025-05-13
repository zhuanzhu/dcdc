package com.egeo.components.order.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.MerchantExpressConfigManage;
import com.egeo.components.order.facade.MerchantExpressConfigFacade;

@Service("merchantExpressConfig")
public class MerchantExpressConfigManageImpl implements MerchantExpressConfigManage{

	
	@Resource(name="merchantExpressConfigFacade")
	private MerchantExpressConfigFacade merchantExpressConfigFacade;
	


}
	