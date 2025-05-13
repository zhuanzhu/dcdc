package com.egeo.components.stock.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.WarehouseAreaManage;
import com.egeo.components.stock.facade.WarehouseAreaFacade;

@Service("warehouseArea")
public class WarehouseAreaManageImpl implements WarehouseAreaManage{

	
	@Resource(name="warehouseAreaFacade")
	private WarehouseAreaFacade warehouseAreaFacade;
	


}
	