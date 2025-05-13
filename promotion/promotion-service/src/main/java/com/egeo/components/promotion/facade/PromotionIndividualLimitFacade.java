package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionIndividualLimitReadService;
import com.egeo.components.promotion.service.write.PromotionIndividualLimitWriteService;


@Component
public class PromotionIndividualLimitFacade {
	
	@Autowired
	private PromotionIndividualLimitReadService promotionIndividualLimitReadService;
	
	@Autowired
	private PromotionIndividualLimitWriteService promotionIndividualLimitWriteService;
	


}
	