package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionIndividualLimitReadService;
import com.egeo.components.promotion.manage.read.PromotionIndividualLimitReadManage;

@Service("promotionIndividualLimitReadService")
public class PromotionIndividualLimitReadServiceImpl implements PromotionIndividualLimitReadService {
	@Autowired
	private PromotionIndividualLimitReadManage promotionIndividualLimitReadManage;
}
	