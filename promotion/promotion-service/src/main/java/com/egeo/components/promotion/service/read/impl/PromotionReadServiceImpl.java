package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionReadService;
import com.egeo.components.promotion.manage.read.PromotionReadManage;

@Service("promotionReadService")
public class PromotionReadServiceImpl implements PromotionReadService {
	@Autowired
	private PromotionReadManage promotionReadManage;
}
	