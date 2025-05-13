package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionLimitManage;
import com.egeo.components.promotion.facade.PromotionLimitFacade;

@Service("promotionLimit")
public class PromotionLimitManageImpl implements PromotionLimitManage{

	
	@Resource(name="promotionLimitFacade")
	private PromotionLimitFacade promotionLimitFacade;
	


}
	