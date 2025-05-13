package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantVideoWriteService;
import com.egeo.components.product.manage.write.MerchantVideoWriteManage;

@Service("merchantVideoWriteService")
public class MerchantVideoWriteServiceImpl  implements MerchantVideoWriteService {
	@Autowired
	private MerchantVideoWriteManage merchantVideoWriteManage;
}
	