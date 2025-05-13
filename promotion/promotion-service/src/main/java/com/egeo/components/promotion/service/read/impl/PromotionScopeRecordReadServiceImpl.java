package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionScopeRecordReadService;
import com.egeo.components.promotion.manage.read.PromotionScopeRecordReadManage;

@Service("promotionScopeRecordReadService")
public class PromotionScopeRecordReadServiceImpl implements PromotionScopeRecordReadService {
	@Autowired
	private PromotionScopeRecordReadManage promotionScopeRecordReadManage;
}
	