package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantSeriesReadService;


@Component
public class MerchantSeriesFacade {
	
	@Resource
	private MerchantSeriesReadService merchantSeriesReadService;
	


}
	