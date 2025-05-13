package com.egeo.components.promotion.business.impl;
	

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PromotionScopeRecordManage;
import com.egeo.components.promotion.facade.PromotionScopeRecordFacade;

@Service("promotionScopeRecord")
public class PromotionScopeRecordManageImpl implements PromotionScopeRecordManage{

	
	@Resource(name="promotionScopeRecordFacade")
	private PromotionScopeRecordFacade promotionScopeRecordFacade;
	


}
	