package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionScopeRecordWriteService;
import com.egeo.components.promotion.manage.write.PromotionScopeRecordWriteManage;

@Service("promotionScopeRecordWriteService")
public class PromotionScopeRecordWriteServiceImpl implements PromotionScopeRecordWriteService {
	@Autowired
	private PromotionScopeRecordWriteManage promotionScopeRecordWriteManage;
}
	