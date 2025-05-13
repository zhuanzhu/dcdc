package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionPriceWriteService;
import com.egeo.components.promotion.manage.write.PromotionPriceWriteManage;

@Service("promotionPriceWriteService")
public class PromotionPriceWriteServiceImpl implements PromotionPriceWriteService {
	@Autowired
	private PromotionPriceWriteManage promotionPriceWriteManage;
}
	