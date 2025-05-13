package com.egeo.components.stock.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.MerchantWarehouseReadService;


@Component
public class MerchantWarehouseFacade {
	
	@Resource
	private MerchantWarehouseReadService merchantWarehouseReadService;
	


}
	