package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionSkuReadService;
import com.egeo.components.promotion.manage.read.PromotionSkuReadManage;

@Service("promotionSkuReadService")
public class PromotionSkuReadServiceImpl implements PromotionSkuReadService {
	@Autowired
	private PromotionSkuReadManage promotionSkuReadManage;
}
	