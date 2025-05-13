package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionRuleManage;
import com.egeo.components.promotion.facade.PromotionRuleFacade;

@Service("promotionRule")
public class PromotionRuleManageImpl implements PromotionRuleManage{

	
	@Resource(name="promotionRuleFacade")
	private PromotionRuleFacade promotionRuleFacade;
	


}
	