package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionLimitRuleReadService;
import com.egeo.components.promotion.manage.read.PromotionLimitRuleReadManage;

@Service("promotionLimitRuleReadService")
public class PromotionLimitRuleReadServiceImpl implements PromotionLimitRuleReadService {
	@Autowired
	private PromotionLimitRuleReadManage promotionLimitRuleReadManage;
}
	