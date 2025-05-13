package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.MerchantProdVideoReadService;
import com.egeo.components.product.manage.read.MerchantProdVideoReadManage;

@Service("merchantProdVideoReadService")
public class MerchantProdVideoReadServiceImpl  implements MerchantProdVideoReadService {
	@Autowired
	private MerchantProdVideoReadManage merchantProdVideoReadManage;
}
	