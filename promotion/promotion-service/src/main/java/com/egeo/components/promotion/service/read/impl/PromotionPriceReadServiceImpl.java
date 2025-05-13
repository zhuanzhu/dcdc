package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionPriceReadService;
import com.egeo.components.promotion.manage.read.PromotionPriceReadManage;

@Service("promotionPriceReadService")
public class PromotionPriceReadServiceImpl implements PromotionPriceReadService {
	@Autowired
	private PromotionPriceReadManage promotionPriceReadManage;
}
	