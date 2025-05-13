package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionConfigReadService;
import com.egeo.components.promotion.manage.read.PromotionConfigReadManage;

@Service("promotionConfigReadService")
public class PromotionConfigReadServiceImpl implements PromotionConfigReadService {
	@Autowired
	private PromotionConfigReadManage promotionConfigReadManage;
}
	