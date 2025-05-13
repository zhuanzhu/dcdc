package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionSoReadService;
import com.egeo.components.promotion.service.write.PromotionSoWriteService;


@Component
public class PromotionSoFacade {
	
	@Autowired
	private PromotionSoReadService promotionSoReadService;
	
	@Autowired
	private PromotionSoWriteService promotionSoWriteService;
	


}
	