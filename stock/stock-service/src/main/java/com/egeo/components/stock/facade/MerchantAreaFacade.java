package com.egeo.components.stock.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.MerchantAreaReadService;


@Component
public class MerchantAreaFacade {
	
	@Resource
	private MerchantAreaReadService merchantAreaReadService;
	


}
	