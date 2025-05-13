package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProductPromotionWriteService;
import com.egeo.components.product.manage.write.MerchantProductPromotionWriteManage;

@Service("merchantProductPromotionWriteService")
public class MerchantProductPromotionWriteServiceImpl  implements MerchantProductPromotionWriteService {
	@Autowired
	private MerchantProductPromotionWriteManage merchantProductPromotionWriteManage;
}
	