package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionSkuManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotionSku")
public class PromotionSkuAction extends BaseSpringController {
	
	@Resource(name="promotionSku")
	private PromotionSkuManage promotionSkuManage;
	
	
}
	