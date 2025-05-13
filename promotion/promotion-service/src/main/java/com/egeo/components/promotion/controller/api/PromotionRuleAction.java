package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionRuleManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotionRule")
public class PromotionRuleAction extends BaseSpringController {
	
	@Resource(name="promotionRule")
	private PromotionRuleManage promotionRuleManage;
	
	
}
	