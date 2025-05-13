package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionSkuReadService;
import com.egeo.components.promotion.service.write.PromotionSkuWriteService;


@Component
public class PromotionSkuFacade {
	
	@Autowired
	private PromotionSkuReadService promotionSkuReadService;
	
	@Autowired
	private PromotionSkuWriteService promotionSkuWriteService;
	


}
	