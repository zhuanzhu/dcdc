package com.egeo.components.stock.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.MerchantWarehouseManage;
import com.egeo.components.stock.facade.MerchantWarehouseFacade;

@Service("merchantWarehouse")
public class MerchantWarehouseManageImpl implements MerchantWarehouseManage{

	
	@Resource(name="merchantWarehouseFacade")
	private MerchantWarehouseFacade merchantWarehouseFacade;
	


}
	