package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.promotion.business.PromotionSoManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/promotion/promotionSo")
public class PromotionSoAction extends BaseSpringController {
	
	@Resource(name="promotionSo")
	private PromotionSoManage promotionSoManage;
	
	
}
	