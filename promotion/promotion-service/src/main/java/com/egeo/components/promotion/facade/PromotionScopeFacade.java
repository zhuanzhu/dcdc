package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionScopeReadService;
import com.egeo.components.promotion.service.write.PromotionScopeWriteService;


@Component
public class PromotionScopeFacade {
	
	@Autowired
	private PromotionScopeReadService promotionScopeReadService;
	
	@Autowired
	private PromotionScopeWriteService promotionScopeWriteService;
	


}
	