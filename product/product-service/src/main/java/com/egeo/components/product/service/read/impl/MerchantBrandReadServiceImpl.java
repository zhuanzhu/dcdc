package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.MerchantBrandReadService;
import com.egeo.components.product.manage.read.MerchantBrandReadManage;

@Service("merchantBrandReadService")
public class MerchantBrandReadServiceImpl  implements MerchantBrandReadService {
	@Autowired
	private MerchantBrandReadManage merchantBrandReadManage;
}
	