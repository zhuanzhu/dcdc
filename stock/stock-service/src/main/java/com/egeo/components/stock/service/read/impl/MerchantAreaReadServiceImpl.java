package com.egeo.components.stock.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.read.MerchantAreaReadService;
import com.egeo.components.stock.manage.read.MerchantAreaReadManage;

@Service("merchantAreaReadService")
public class MerchantAreaReadServiceImpl  implements MerchantAreaReadService {
	@Autowired
	private MerchantAreaReadManage merchantAreaReadManage;
}
	