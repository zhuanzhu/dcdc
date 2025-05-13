package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionIndividualLimitManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotionIndividualLimit")
public class PromotionIndividualLimitAction extends BaseSpringController {
	
	@Resource(name="promotionIndividualLimit")
	private PromotionIndividualLimitManage promotionIndividualLimitManage;
	
	
}
	