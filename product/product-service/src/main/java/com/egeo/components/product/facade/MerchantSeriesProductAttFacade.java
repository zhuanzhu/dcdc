package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantSeriesProductAttReadService;


@Component
public class MerchantSeriesProductAttFacade {
	
	@Resource
	private MerchantSeriesProductAttReadService merchantSeriesProductAttReadService;
	


}
	