package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionRuleWriteService;
import com.egeo.components.promotion.manage.write.PromotionRuleWriteManage;

@Service("promotionRuleWriteService")
public class PromotionRuleWriteServiceImpl implements PromotionRuleWriteService {
	@Autowired
	private PromotionRuleWriteManage promotionRuleWriteManage;
}
	