package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionLimitRuleManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotionLimitRule")
public class PromotionLimitRuleAction extends BaseSpringController {
	
	@Resource(name="promotionLimitRule")
	private PromotionLimitRuleManage promotionLimitRuleManage;
	
	
}
	