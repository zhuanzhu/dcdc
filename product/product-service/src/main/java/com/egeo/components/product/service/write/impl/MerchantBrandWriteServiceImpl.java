package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantBrandWriteService;
import com.egeo.components.product.manage.write.MerchantBrandWriteManage;

@Service("merchantBrandWriteService")
public class MerchantBrandWriteServiceImpl  implements MerchantBrandWriteService {
	@Autowired
	private MerchantBrandWriteManage merchantBrandWriteManage;
}
	