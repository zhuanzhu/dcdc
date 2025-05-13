package com.egeo.components.stock.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.MerchantProductActivityStockReadService;


@Component
public class MerchantProductActivityStockFacade {
	
	@Resource
	private MerchantProductActivityStockReadService merchantProductActivityStockReadService;
	


}
	