package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProdFlashWriteService;
import com.egeo.components.product.manage.write.MerchantProdFlashWriteManage;

@Service("merchantProdFlashWriteService")
public class MerchantProdFlashWriteServiceImpl  implements MerchantProdFlashWriteService {
	@Autowired
	private MerchantProdFlashWriteManage merchantProdFlashWriteManage;
}
	