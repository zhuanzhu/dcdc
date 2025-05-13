package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionLimitReadService;
import com.egeo.components.promotion.manage.read.PromotionLimitReadManage;

@Service("promotionLimitReadService")
public class PromotionLimitReadServiceImpl implements PromotionLimitReadService {
	@Autowired
	private PromotionLimitReadManage promotionLimitReadManage;
}
	