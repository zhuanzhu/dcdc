package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionIndividualLimitWriteService;
import com.egeo.components.promotion.manage.write.PromotionIndividualLimitWriteManage;

@Service("promotionIndividualLimitWriteService")
public class PromotionIndividualLimitWriteServiceImpl implements PromotionIndividualLimitWriteService {
	@Autowired
	private PromotionIndividualLimitWriteManage promotionIndividualLimitWriteManage;
}
	