package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionGiftItemManage;
import com.egeo.components.promotion.facade.PromotionGiftItemFacade;

@Service("promotionGiftItem")
public class PromotionGiftItemManageImpl implements PromotionGiftItemManage{

	
	@Resource(name="promotionGiftItemFacade")
	private PromotionGiftItemFacade promotionGiftItemFacade;
	


}
	