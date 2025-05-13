package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionSoWriteService;
import com.egeo.components.promotion.manage.write.PromotionSoWriteManage;

@Service("promotionSoWriteService")
public class PromotionSoWriteServiceImpl implements PromotionSoWriteService {
	@Autowired
	private PromotionSoWriteManage promotionSoWriteManage;
}
	