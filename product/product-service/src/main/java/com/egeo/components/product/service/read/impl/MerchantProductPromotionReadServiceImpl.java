package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.MerchantProductPromotionReadService;
import com.egeo.components.product.manage.read.MerchantProductPromotionReadManage;

@Service("merchantProductPromotionReadService")
public class MerchantProductPromotionReadServiceImpl  implements MerchantProductPromotionReadService {
	@Autowired
	private MerchantProductPromotionReadManage merchantProductPromotionReadManage;
}
	