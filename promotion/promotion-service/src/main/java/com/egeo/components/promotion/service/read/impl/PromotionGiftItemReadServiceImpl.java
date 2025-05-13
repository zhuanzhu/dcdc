package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionGiftItemReadService;
import com.egeo.components.promotion.manage.read.PromotionGiftItemReadManage;

@Service("promotionGiftItemReadService")
public class PromotionGiftItemReadServiceImpl implements PromotionGiftItemReadService {
	@Autowired
	private PromotionGiftItemReadManage promotionGiftItemReadManage;
}
	