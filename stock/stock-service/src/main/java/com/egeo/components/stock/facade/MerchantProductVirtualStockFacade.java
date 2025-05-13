package com.egeo.components.stock.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.MerchantProductVirtualStockReadService;


@Component
public class MerchantProductVirtualStockFacade {
	
	@Resource
	private MerchantProductVirtualStockReadService merchantProductVirtualStockReadService;
	


}
	