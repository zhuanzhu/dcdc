package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotion")
public class PromotionAction extends BaseSpringController {
	
	@Resource(name="promotion")
	private PromotionManage promotionManage;
	
	
}
	