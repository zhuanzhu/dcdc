package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionGiftItemWriteService;
import com.egeo.components.promotion.manage.write.PromotionGiftItemWriteManage;

@Service("promotionGiftItemWriteService")
public class PromotionGiftItemWriteServiceImpl implements PromotionGiftItemWriteService {
	@Autowired
	private PromotionGiftItemWriteManage promotionGiftItemWriteManage;
}
	