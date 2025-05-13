package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionScopeManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotionScope")
public class PromotionScopeAction extends BaseSpringController {
	
	@Resource(name="promotionScope")
	private PromotionScopeManage promotionScopeManage;
	
	
}
	