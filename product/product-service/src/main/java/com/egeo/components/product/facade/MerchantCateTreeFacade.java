package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantCateTreeReadService;


@Component
public class MerchantCateTreeFacade {
	
	@Resource
	private MerchantCateTreeReadService merchantCateTreeReadService;
	


}
	