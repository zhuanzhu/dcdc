package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionLimitRuleReadService;
import com.egeo.components.promotion.service.write.PromotionLimitRuleWriteService;


@Component
public class PromotionLimitRuleFacade {
	
	@Autowired
	private PromotionLimitRuleReadService promotionLimitRuleReadService;
	
	@Autowired
	private PromotionLimitRuleWriteService promotionLimitRuleWriteService;
	


}
	