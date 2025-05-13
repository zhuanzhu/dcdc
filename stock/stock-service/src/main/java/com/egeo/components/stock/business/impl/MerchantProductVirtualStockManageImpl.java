package com.egeo.components.stock.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.MerchantProductVirtualStockManage;
import com.egeo.components.stock.facade.MerchantProductVirtualStockFacade;

@Service("merchantProductVirtualStock")
public class MerchantProductVirtualStockManageImpl implements MerchantProductVirtualStockManage{

	
	@Resource(name="merchantProductVirtualStockFacade")
	private MerchantProductVirtualStockFacade merchantProductVirtualStockFacade;
	


}
	