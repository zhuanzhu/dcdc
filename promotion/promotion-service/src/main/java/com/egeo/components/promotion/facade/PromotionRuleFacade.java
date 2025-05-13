package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionRuleReadService;
import com.egeo.components.promotion.service.write.PromotionRuleWriteService;


@Component
public class PromotionRuleFacade {
	
	@Autowired
	private PromotionRuleReadService promotionRuleReadService;
	
	@Autowired
	private PromotionRuleWriteService promotionRuleWriteService;
	


}
	