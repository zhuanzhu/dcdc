package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.MerchantAreaWriteService;
import com.egeo.components.stock.manage.write.MerchantAreaWriteManage;

@Service("merchantAreaWriteService")
public class MerchantAreaWriteServiceImpl  implements MerchantAreaWriteService {
	@Autowired
	private MerchantAreaWriteManage merchantAreaWriteManage;
}
	