package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProdVideoWriteService;
import com.egeo.components.product.manage.write.MerchantProdVideoWriteManage;

@Service("merchantProdVideoWriteService")
public class MerchantProdVideoWriteServiceImpl  implements MerchantProdVideoWriteService {
	@Autowired
	private MerchantProdVideoWriteManage merchantProdVideoWriteManage;
}
	