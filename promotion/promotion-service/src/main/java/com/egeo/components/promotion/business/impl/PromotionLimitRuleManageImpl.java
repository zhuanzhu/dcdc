package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionLimitRuleManage;
import com.egeo.components.promotion.facade.PromotionLimitRuleFacade;

@Service("promotionLimitRule")
public class PromotionLimitRuleManageImpl implements PromotionLimitRuleManage{

	
	@Resource(name="promotionLimitRuleFacade")
	private PromotionLimitRuleFacade promotionLimitRuleFacade;
	


}
	