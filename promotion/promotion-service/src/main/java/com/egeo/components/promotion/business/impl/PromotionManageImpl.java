package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionManage;
import com.egeo.components.promotion.facade.PromotionFacade;

@Service("promotion")
public class PromotionManageImpl implements PromotionManage{

	
	@Resource(name="promotionFacade")
	private PromotionFacade promotionFacade;
	


}
	