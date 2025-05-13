package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.MerchantExpressConfigReadService;
import com.egeo.components.order.manage.read.MerchantExpressConfigReadManage;

@Service("merchantExpressConfigReadService")
public class MerchantExpressConfigReadServiceImpl  implements MerchantExpressConfigReadService {
	@Autowired
	private MerchantExpressConfigReadManage merchantExpressConfigReadManage;
}
	