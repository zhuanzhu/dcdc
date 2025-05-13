package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantBrandManage;
import com.egeo.components.product.facade.MerchantBrandFacade;

@Service("merchantBrand")
public class MerchantBrandManageImpl implements MerchantBrandManage{

	
	@Resource(name="merchantBrandFacade")
	private MerchantBrandFacade merchantBrandFacade;
	


}
	