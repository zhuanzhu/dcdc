package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionIndividualLimitManage;
import com.egeo.components.promotion.facade.PromotionIndividualLimitFacade;

@Service("promotionIndividualLimit")
public class PromotionIndividualLimitManageImpl implements PromotionIndividualLimitManage{

	
	@Resource(name="promotionIndividualLimitFacade")
	private PromotionIndividualLimitFacade promotionIndividualLimitFacade;
	


}
	