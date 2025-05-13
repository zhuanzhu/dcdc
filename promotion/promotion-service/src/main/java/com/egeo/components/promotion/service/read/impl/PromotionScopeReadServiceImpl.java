package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionScopeReadService;
import com.egeo.components.promotion.manage.read.PromotionScopeReadManage;

@Service("promotionScopeReadService")
public class PromotionScopeReadServiceImpl implements PromotionScopeReadService {
	@Autowired
	private PromotionScopeReadManage promotionScopeReadManage;
}
	