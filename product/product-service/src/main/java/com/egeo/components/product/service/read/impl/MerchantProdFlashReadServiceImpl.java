package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.MerchantProdFlashReadService;
import com.egeo.components.product.manage.read.MerchantProdFlashReadManage;

@Service("merchantProdFlashReadService")
public class MerchantProdFlashReadServiceImpl  implements MerchantProdFlashReadService {
	@Autowired
	private MerchantProdFlashReadManage merchantProdFlashReadManage;
}
	