package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProdFlashManage;
import com.egeo.components.product.facade.MerchantProdFlashFacade;

@Service("merchantProdFlash")
public class MerchantProdFlashManageImpl implements MerchantProdFlashManage{

	
	@Resource(name="merchantProdFlashFacade")
	private MerchantProdFlashFacade merchantProdFlashFacade;
	


}
	