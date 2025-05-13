package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionScopeManage;
import com.egeo.components.promotion.facade.PromotionScopeFacade;

@Service("promotionScope")
public class PromotionScopeManageImpl implements PromotionScopeManage{

	
	@Resource(name="promotionScopeFacade")
	private PromotionScopeFacade promotionScopeFacade;
	


}
	