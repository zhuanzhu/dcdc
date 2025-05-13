package com.egeo.components.stock.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.MerchantProductActivityStockManage;
import com.egeo.components.stock.facade.MerchantProductActivityStockFacade;

@Service("merchantProductActivityStock")
public class MerchantProductActivityStockManageImpl implements MerchantProductActivityStockManage{

	
	@Resource(name="merchantProductActivityStockFacade")
	private MerchantProductActivityStockFacade merchantProductActivityStockFacade;
	


}
	