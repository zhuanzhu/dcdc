package com.egeo.components.stock.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.read.MerchantProductAreaReadService;
import com.egeo.components.stock.manage.read.MerchantProductAreaReadManage;

@Service("merchantProductAreaReadService")
public class MerchantProductAreaReadServiceImpl  implements MerchantProductAreaReadService {
	@Autowired
	private MerchantProductAreaReadManage merchantProductAreaReadManage;
}
	