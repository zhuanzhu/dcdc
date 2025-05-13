package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionLimitReadService;
import com.egeo.components.promotion.service.write.PromotionLimitWriteService;


@Component
public class PromotionLimitFacade {
	
	@Autowired
	private PromotionLimitReadService promotionLimitReadService;
	
	@Autowired
	private PromotionLimitWriteService promotionLimitWriteService;
	


}
	