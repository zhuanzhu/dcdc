package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantBrandReadService;


@Component
public class MerchantBrandFacade {
	
	@Resource
	private MerchantBrandReadService merchantBrandReadService;
	


}
	