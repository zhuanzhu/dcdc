package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantCateTreeWriteService;
import com.egeo.components.product.manage.write.MerchantCateTreeWriteManage;

@Service("merchantCateTreeWriteService")
public class MerchantCateTreeWriteServiceImpl  implements MerchantCateTreeWriteService {
	@Autowired
	private MerchantCateTreeWriteManage merchantCateTreeWriteManage;
}
	