package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantVideoManage;
import com.egeo.components.product.facade.MerchantVideoFacade;

@Service("merchantVideo")
public class MerchantVideoManageImpl implements MerchantVideoManage{

	
	@Resource(name="merchantVideoFacade")
	private MerchantVideoFacade merchantVideoFacade;
	


}
	