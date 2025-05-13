package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionGiftItemReadService;
import com.egeo.components.promotion.service.write.PromotionGiftItemWriteService;


@Component
public class PromotionGiftItemFacade {
	
	@Autowired
	private PromotionGiftItemReadService promotionGiftItemReadService;
	
	@Autowired
	private PromotionGiftItemWriteService promotionGiftItemWriteService;
	


}
	