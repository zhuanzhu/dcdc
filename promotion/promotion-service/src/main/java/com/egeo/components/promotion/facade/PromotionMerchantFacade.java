package com.egeo.components.promotion.facade;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PromotionMerchantReadService;
import com.egeo.components.promotion.service.write.PromotionMerchantWriteService;


@Component
public class PromotionMerchantFacade {
	
	@Autowired
	private PromotionMerchantReadService promotionMerchantReadService;
	
	@Autowired
	private PromotionMerchantWriteService promotionMerchantWriteService;
	


}
	