package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionPriceManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotionPrice")
public class PromotionPriceAction extends BaseSpringController {
	
	@Resource(name="promotionPrice")
	private PromotionPriceManage promotionPriceManage;
	
	
}
	