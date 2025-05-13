package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionGiftItemManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotionGiftItem")
public class PromotionGiftItemAction extends BaseSpringController {
	
	@Resource(name="promotionGiftItem")
	private PromotionGiftItemManage promotionGiftItemManage;
	
	
}
	