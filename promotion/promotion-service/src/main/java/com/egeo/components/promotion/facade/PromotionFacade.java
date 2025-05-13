package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionReadService;
import com.egeo.components.promotion.service.write.PromotionWriteService;


@Component
public class PromotionFacade {
	
	@Autowired
	private PromotionReadService promotionReadService;
	
	@Autowired
	private PromotionWriteService promotionWriteService;
	


}
	