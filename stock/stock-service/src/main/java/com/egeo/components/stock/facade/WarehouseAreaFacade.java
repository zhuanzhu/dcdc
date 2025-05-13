package com.egeo.components.stock.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.WarehouseAreaReadService;


@Component
public class WarehouseAreaFacade {
	
	@Resource
	private WarehouseAreaReadService warehouseAreaReadService;
	


}
	