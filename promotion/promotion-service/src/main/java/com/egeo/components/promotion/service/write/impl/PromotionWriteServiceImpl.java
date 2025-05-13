package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionWriteService;
import com.egeo.components.promotion.manage.write.PromotionWriteManage;

@Service("promotionWriteService")
public class PromotionWriteServiceImpl implements PromotionWriteService {
	@Autowired
	private PromotionWriteManage promotionWriteManage;
}
	