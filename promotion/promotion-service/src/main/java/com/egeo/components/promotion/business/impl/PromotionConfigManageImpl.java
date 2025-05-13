package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionConfigManage;
import com.egeo.components.promotion.facade.PromotionConfigFacade;

@Service("promotionConfig")
public class PromotionConfigManageImpl implements PromotionConfigManage{

	
	@Resource(name="promotionConfigFacade")
	private PromotionConfigFacade promotionConfigFacade;
	


}
	