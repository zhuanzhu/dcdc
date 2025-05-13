package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionScopeWriteService;
import com.egeo.components.promotion.manage.write.PromotionScopeWriteManage;

@Service("promotionScopeWriteService")
public class PromotionScopeWriteServiceImpl implements PromotionScopeWriteService {
	@Autowired
	private PromotionScopeWriteManage promotionScopeWriteManage;
}
	