package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProdVideoManage;
import com.egeo.components.product.facade.MerchantProdVideoFacade;

@Service("merchantProdVideo")
public class MerchantProdVideoManageImpl implements MerchantProdVideoManage{

	
	@Resource(name="merchantProdVideoFacade")
	private MerchantProdVideoFacade merchantProdVideoFacade;
	


}
	