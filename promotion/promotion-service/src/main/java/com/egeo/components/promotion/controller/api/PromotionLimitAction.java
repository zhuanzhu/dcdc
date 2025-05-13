package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionLimitManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotionLimit")
public class PromotionLimitAction extends BaseSpringController {
	
	@Resource(name="promotionLimit")
	private PromotionLimitManage promotionLimitManage;
	
	
}
	