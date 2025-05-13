package com.egeo.components.stock.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.MerchantProductAreaReadService;


@Component
public class MerchantProductAreaFacade {
	
	@Resource
	private MerchantProductAreaReadService merchantProductAreaReadService;
	


}
	