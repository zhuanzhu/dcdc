package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionConfigWriteService;
import com.egeo.components.promotion.manage.write.PromotionConfigWriteManage;

@Service("promotionConfigWriteService")
public class PromotionConfigWriteServiceImpl implements PromotionConfigWriteService {
	@Autowired
	private PromotionConfigWriteManage promotionConfigWriteManage;
}
	