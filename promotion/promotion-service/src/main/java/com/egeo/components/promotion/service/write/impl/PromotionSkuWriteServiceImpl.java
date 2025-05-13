package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionSkuWriteService;
import com.egeo.components.promotion.manage.write.PromotionSkuWriteManage;

@Service("promotionSkuWriteService")
public class PromotionSkuWriteServiceImpl implements PromotionSkuWriteService {
	@Autowired
	private PromotionSkuWriteManage promotionSkuWriteManage;
}
	