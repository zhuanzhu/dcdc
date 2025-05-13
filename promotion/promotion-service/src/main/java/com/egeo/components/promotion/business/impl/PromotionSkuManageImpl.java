package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionSkuManage;
import com.egeo.components.promotion.facade.PromotionSkuFacade;

@Service("promotionSku")
public class PromotionSkuManageImpl implements PromotionSkuManage{

	
	@Resource(name="promotionSkuFacade")
	private PromotionSkuFacade promotionSkuFacade;
	


}
	