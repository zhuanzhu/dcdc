package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantVideoReadService;


@Component
public class MerchantVideoFacade {
	
	@Resource
	private MerchantVideoReadService merchantVideoReadService;
	


}
	