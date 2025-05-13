package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionLimitRuleWriteService;
import com.egeo.components.promotion.manage.write.PromotionLimitRuleWriteManage;

@Service("promotionLimitRuleWriteService")
public class PromotionLimitRuleWriteServiceImpl implements PromotionLimitRuleWriteService {
	@Autowired
	private PromotionLimitRuleWriteManage promotionLimitRuleWriteManage;
}
	