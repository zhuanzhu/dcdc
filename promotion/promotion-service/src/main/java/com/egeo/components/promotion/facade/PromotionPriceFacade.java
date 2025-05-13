package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionPriceReadService;
import com.egeo.components.promotion.service.write.PromotionPriceWriteService;


@Component
public class PromotionPriceFacade {
	
	@Autowired
	private PromotionPriceReadService promotionPriceReadService;
	
	@Autowired
	private PromotionPriceWriteService promotionPriceWriteService;
	


}
	