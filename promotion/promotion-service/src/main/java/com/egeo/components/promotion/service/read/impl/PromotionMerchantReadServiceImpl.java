package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.read.PromotionMerchantReadService;
import com.egeo.components.promotion.manage.read.PromotionMerchantReadManage;

@Service("promotionMerchantReadService")
public class PromotionMerchantReadServiceImpl implements PromotionMerchantReadService {
	@Autowired
	private PromotionMerchantReadManage promotionMerchantReadManage;
}
	