package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.MerchantProductAreaWriteService;
import com.egeo.components.stock.manage.write.MerchantProductAreaWriteManage;

@Service("merchantProductAreaWriteService")
public class MerchantProductAreaWriteServiceImpl  implements MerchantProductAreaWriteService {
	@Autowired
	private MerchantProductAreaWriteManage merchantProductAreaWriteManage;
}
	