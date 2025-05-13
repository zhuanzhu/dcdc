package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.MerchantExpressConfigWriteService;
import com.egeo.components.order.manage.write.MerchantExpressConfigWriteManage;

@Service("merchantExpressConfigWriteService")
public class MerchantExpressConfigWriteServiceImpl  implements MerchantExpressConfigWriteService {
	@Autowired
	private MerchantExpressConfigWriteManage merchantExpressConfigWriteManage;
}
	