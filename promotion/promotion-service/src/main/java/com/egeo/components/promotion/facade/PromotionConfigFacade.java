package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionConfigReadService;
import com.egeo.components.promotion.service.write.PromotionConfigWriteService;


@Component
public class PromotionConfigFacade {
	
	@Autowired
	private PromotionConfigReadService promotionConfigReadService;
	
	@Autowired
	private PromotionConfigWriteService promotionConfigWriteService;
	


}
	