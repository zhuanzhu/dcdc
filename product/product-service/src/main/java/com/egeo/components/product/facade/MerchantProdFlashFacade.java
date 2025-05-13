package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProdFlashReadService;


@Component
public class MerchantProdFlashFacade {
	
	@Resource
	private MerchantProdFlashReadService merchantProdFlashReadService;
	


}
	