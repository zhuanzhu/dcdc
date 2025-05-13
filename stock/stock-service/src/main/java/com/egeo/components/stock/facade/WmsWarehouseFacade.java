package com.egeo.components.stock.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.WmsWarehouseReadService;


@Component
public class WmsWarehouseFacade {
	
	@Resource
	private WmsWarehouseReadService wmsWarehouseReadService;
	


}
	