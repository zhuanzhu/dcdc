package com.egeo.components.stock.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.MerchantProductWarehouseReadService;


@Component
public class MerchantProductWarehouseFacade {
	
	@Resource
	private MerchantProductWarehouseReadService merchantProductWarehouseReadService;
	


}
	