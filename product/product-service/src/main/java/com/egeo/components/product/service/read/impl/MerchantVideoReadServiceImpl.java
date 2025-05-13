package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.MerchantVideoReadService;
import com.egeo.components.product.manage.read.MerchantVideoReadManage;

@Service("merchantVideoReadService")
public class MerchantVideoReadServiceImpl  implements MerchantVideoReadService {
	@Autowired
	private MerchantVideoReadManage merchantVideoReadManage;
}
	