package com.egeo.components.stock.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.WmsWarehouseManage;
import com.egeo.components.stock.facade.WmsWarehouseFacade;

@Service("wmsWarehouse")
public class WmsWarehouseManageImpl implements WmsWarehouseManage{

	
	@Resource(name="wmsWarehouseFacade")
	private WmsWarehouseFacade wmsWarehouseFacade;
	


}
	