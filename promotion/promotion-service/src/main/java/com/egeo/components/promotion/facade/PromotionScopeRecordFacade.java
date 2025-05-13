package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionScopeRecordReadService;
import com.egeo.components.promotion.service.write.PromotionScopeRecordWriteService;


@Component
public class PromotionScopeRecordFacade {
	
	@Autowired
	private PromotionScopeRecordReadService promotionScopeRecordReadService;
	
	@Autowired
	private PromotionScopeRecordWriteService promotionScopeRecordWriteService;
	


}
	