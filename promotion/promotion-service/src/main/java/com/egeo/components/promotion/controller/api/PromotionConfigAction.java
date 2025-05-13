package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionConfigManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotionConfig")
public class PromotionConfigAction extends BaseSpringController {
	
	@Resource(name="promotionConfig")
	private PromotionConfigManage promotionConfigManage;
	
	
}
	