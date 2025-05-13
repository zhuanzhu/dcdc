package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantCateTreeManage;
import com.egeo.components.product.facade.MerchantCateTreeFacade;

@Service("merchantCateTree")
public class MerchantCateTreeManageImpl implements MerchantCateTreeManage{

	
	@Resource(name="merchantCateTreeFacade")
	private MerchantCateTreeFacade merchantCateTreeFacade;
	


}
	