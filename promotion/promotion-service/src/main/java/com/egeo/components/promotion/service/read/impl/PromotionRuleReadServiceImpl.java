package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionRuleReadService;
import com.egeo.components.promotion.manage.read.PromotionRuleReadManage;

@Service("promotionRuleReadService")
public class PromotionRuleReadServiceImpl implements PromotionRuleReadService {
	@Autowired
	private PromotionRuleReadManage promotionRuleReadManage;
}
	