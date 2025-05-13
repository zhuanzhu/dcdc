package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProdVideoReadService;


@Component
public class MerchantProdVideoFacade {
	
	@Resource
	private MerchantProdVideoReadService merchantProdVideoReadService;
	


}
	