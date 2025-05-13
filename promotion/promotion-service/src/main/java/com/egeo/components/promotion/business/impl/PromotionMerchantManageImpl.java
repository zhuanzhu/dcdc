package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionMerchantManage;
import com.egeo.components.promotion.facade.PromotionMerchantFacade;

@Service("promotionMerchant")
public class PromotionMerchantManageImpl implements PromotionMerchantManage{

	
	@Resource(name="promotionMerchantFacade")
	private PromotionMerchantFacade promotionMerchantFacade;
	


}
	