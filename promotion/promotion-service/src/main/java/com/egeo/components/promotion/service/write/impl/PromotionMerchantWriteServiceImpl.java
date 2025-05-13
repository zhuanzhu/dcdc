package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PromotionMerchantWriteService;
import com.egeo.components.promotion.manage.write.PromotionMerchantWriteManage;

@Service("promotionMerchantWriteService")
public class PromotionMerchantWriteServiceImpl implements PromotionMerchantWriteService {
	@Autowired
	private PromotionMerchantWriteManage promotionMerchantWriteManage;
}
	