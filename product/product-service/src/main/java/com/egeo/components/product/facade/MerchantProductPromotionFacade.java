package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProductPromotionReadService;


@Component
public class MerchantProductPromotionFacade {
	
	@Resource
	private MerchantProductPromotionReadService merchantProductPromotionReadService;
	


}
	