package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionSoManage;
import com.egeo.components.promotion.facade.PromotionSoFacade;

@Service("promotionSo")
public class PromotionSoManageImpl implements PromotionSoManage{

	
	@Resource(name="promotionSoFacade")
	private PromotionSoFacade promotionSoFacade;
	


}
	