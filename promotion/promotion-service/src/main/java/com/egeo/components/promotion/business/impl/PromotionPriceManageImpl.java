package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionPriceManage;
import com.egeo.components.promotion.facade.PromotionPriceFacade;

@Service("promotionPrice")
public class PromotionPriceManageImpl implements PromotionPriceManage{

	
	@Resource(name="promotionPriceFacade")
	private PromotionPriceFacade promotionPriceFacade;
	


}
	