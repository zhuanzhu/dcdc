package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionSoReadService;
import com.egeo.components.promotion.manage.read.PromotionSoReadManage;

@Service("promotionSoReadService")
public class PromotionSoReadServiceImpl implements PromotionSoReadService {
	@Autowired
	private PromotionSoReadManage promotionSoReadManage;
}
	